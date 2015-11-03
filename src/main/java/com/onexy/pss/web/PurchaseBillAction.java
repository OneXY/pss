package com.onexy.pss.web;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.onexy.pss.domain.Employee;
import com.onexy.pss.domain.PurchaseBill;
import com.onexy.pss.domain.PurchaseBillItem;
import com.onexy.pss.page.PageResult;
import com.onexy.pss.page.PurchaseBillQuery;
import com.onexy.pss.service.IEmployeeService;
import com.onexy.pss.service.IPurchaseBillService;
import com.onexy.pss.service.ISupplierService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class PurchaseBillAction extends CRUDAction<PurchaseBill> {
	private String name;

	private PurchaseBill purchaseBill;
	
	private ISupplierService supplierService;
	
	private IEmployeeService employeeService;

	private PurchaseBillQuery baseQuery = new PurchaseBillQuery();

	private PageResult<PurchaseBill> pageResult;

	private IPurchaseBillService purchaseBillService;


	public void setName(String name) {
		this.name = name;
	}

	public PageResult<PurchaseBill> getPageResult() {
		return pageResult;
	}

	public PurchaseBillQuery getBaseQuery() {
		return baseQuery;
	}
	
	public void setSupplierService(ISupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public void setBaseQuery(PurchaseBillQuery baseQuery) {
		logger.info("正在注入:baseQuery...");
		this.baseQuery = baseQuery;
	}

	public void setPurchaseBillService(IPurchaseBillService purchaseBillService) {
		logger.info("正在注入:purchaseBillService...");
		this.purchaseBillService = purchaseBillService;
	}

	@Override
	public String input() throws Exception {
		putContext("allSuppliers", supplierService.getAll());
		putContext("allBuyers", employeeService.findBuerysByDeptName("采购部"));
		return INPUT;
	}

	public void validateSave() {
//		if (StringUtils.isBlank(purchaseBill.getName())) {
//			addFieldError("name", "用户名必须填写");
//		}
	}

	@Override
	@InputConfig(methodName = "input")
	public String save() throws Exception {
		Employee loginUser = getLoginUser();
		List<PurchaseBillItem> items = purchaseBill.getItems();//老师说这里是建立一方到多方的关系,我觉得是再表单提交过来的时候,框架已经建立了该关系,即调用setter的时候
		BigDecimal totalAmount = new BigDecimal(0);
		BigDecimal totalNum = new BigDecimal(0);
		for (PurchaseBillItem purchaseBillItem : items) {
			purchaseBillItem.setBill(purchaseBill);//建立多方到一方的关系
			purchaseBillItem.setAmount(purchaseBillItem.getPrice().multiply(purchaseBillItem.getNum()));//计算总金额
			totalAmount = totalAmount.add(purchaseBillItem.getAmount());
			totalNum = totalNum.add(purchaseBillItem.getNum());
		}
		purchaseBill.setTotalAmount(totalAmount);
		purchaseBill.setTotalNum(totalNum);
		try {
			if (id != null) {
				if (purchaseBill.getStatus() != 0) {
					addActionError("已经审核,不能修改,已经记录在案");
					return RELOAD;
				}
				purchaseBillService.update(purchaseBill);
				addActionMessage("修改成功");
			} else {
				purchaseBill.setInputUser(loginUser);
				purchaseBillService.save(purchaseBill);
				addActionMessage("添加成功");
				baseQuery.setCurrentPage(256);
			}
		} catch (Exception e) {
			addActionError("业务逻辑异常:" + e.getMessage());
			return input();
		}
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (id != null) {
			purchaseBill = purchaseBillService.get(id);
			if (purchaseBill.getStatus() != 0) {
				out.print("{\"success\":false,\"msg\":\"不允许删除\"}");
				return NONE;
			}
			try {
				purchaseBillService.delete(id);
				out.print("{\"success\":true,\"msg\":\"删除成功\"}");
			} catch (Exception e) {
				out.print("{\"success\":false,\"msg\":\"" + e.getMessage()
						+ "\"}");
			}
		} else {
			out.print("{\"success\":false,\"msg\":\"没有删除的"+id+"\"}");
		}
		return NONE;
	}
	
//	public String auditor() throws Exception {
//		String msg = purchaseBillService.auditor(id, getLoginUser());
//		addActionMessage(msg);
//		return RELOAD;
//	}
	public String auditor() throws Exception {
		String msg = purchaseBillService.auditor(id, getLoginUser());
		
		putContext("map", msg);
		return JSON_TYPE;
	}

	@InputConfig(methodName = "input")
	public void checkName() throws Exception {
		logger.info("验证用户名为:" + name + "的用户是否存在");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
//		if (id != null && purchaseBillService.get(id).getName().equals(name)) {
//			out.print(true);
//		} else {
//			out.print(purchaseBillService.findByName(name));
//		}
	}

	@Override
	protected String list() throws Exception {
		pageResult = purchaseBillService.findPageResult(baseQuery);
		return SUCCESS;
	}

	@Override
	protected void beforeInput() throws Exception {
		if (id != null) {
			this.purchaseBill = purchaseBillService.get(id);
		}else {
			this.purchaseBill = new PurchaseBill();
			purchaseBill.setVdate(new Date());
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		if (id != null) {
			this.purchaseBill = purchaseBillService.get(id);
			purchaseBill.setSupplier(null);
			purchaseBill.setBuyer(null);
			purchaseBill.getItems().clear();
		} else {
			this.purchaseBill = new PurchaseBill();
		}
	}

	@Override
	public PurchaseBill getModel() {
		return purchaseBill;
	}

}
