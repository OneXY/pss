package com.onexy.pss.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.onexy.pss.domain.Employee;
import com.onexy.pss.domain.StockincomeBill;
import com.onexy.pss.domain.StockincomeBillItem;
import com.onexy.pss.page.PageResult;
import com.onexy.pss.page.StockincomeBillQuery;
import com.onexy.pss.service.IDepotService;
import com.onexy.pss.service.IEmployeeService;
import com.onexy.pss.service.IStockincomeBillService;
import com.onexy.pss.service.ISupplierService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class StockincomeBillAction extends CRUDAction<StockincomeBill> {
	private String name;

	private StockincomeBill stockincomeBill;
	
	private ISupplierService supplierService;
	
	private IEmployeeService employeeService;
	
	private IDepotService depotService;

	private StockincomeBillQuery baseQuery = new StockincomeBillQuery();

	private PageResult<StockincomeBill> pageResult;

	private IStockincomeBillService stockincomeBillService;


	public void setName(String name) {
		this.name = name;
	}

	public PageResult<StockincomeBill> getPageResult() {
		return pageResult;
	}

	public StockincomeBillQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(StockincomeBillQuery baseQuery) {
		logger.info("正在注入:baseQuery...");
		this.baseQuery = baseQuery;
	}

	public void setStockincomeBillService(IStockincomeBillService stockincomeBillService) {
		logger.info("正在注入:stockincomeBillService...");
		this.stockincomeBillService = stockincomeBillService;
	}
	
	public void setSupplierService(ISupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public void setDepotService(IDepotService depotService) {
		this.depotService = depotService;
	}
	
	@Override
	public String input() throws Exception {
		putContext("allSuppliers", supplierService.getAll());
		putContext("allKeepers", employeeService.findKeepersByDeptName("库管部"));
		putContext("allDepots", depotService.getAll());
		return INPUT;
	}

	@Override
	@InputConfig(methodName = "input")
	public String save() throws Exception {
		try {
			Employee loginUser = getLoginUser();
			List<StockincomeBillItem> items = stockincomeBill.getItems();
			BigDecimal totalAmount = new BigDecimal(0);
			BigDecimal totalNum = new BigDecimal(0);
			for (StockincomeBillItem stockincomeBillItem : items) {
				stockincomeBillItem.setBill(stockincomeBill);
				stockincomeBillItem.setAmount(stockincomeBillItem.getPrice().multiply(stockincomeBillItem.getNum()));
				totalAmount = totalAmount.add(stockincomeBillItem.getAmount());
				totalNum = totalNum.add(stockincomeBillItem.getNum());
			}
			stockincomeBill.setTotalAmount(totalAmount);
			stockincomeBill.setTotalNum(totalNum);
			if (id != null) {
				stockincomeBillService.update(stockincomeBill);
				addActionMessage("修改成功");
			} else {
				stockincomeBill.setInputUser(loginUser);
				stockincomeBillService.save(stockincomeBill);
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
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		if (id != null) {
			try {
				stockincomeBillService.delete(id);
				map.put("success", true);
				map.put("msg", "删除成功");
			} catch (Exception e) {
				map.put("msg", "异常:" + e.getMessage());
			}
		} else {
			map.put("msg", "没有删除的"+id);
		}
		putContext("map", map);
		return JSON_TYPE;
	}

	@Override
	protected String list() throws Exception {
		pageResult = stockincomeBillService.findPageResult(baseQuery);
//		putContext("depts", departmentService.getAll());
		return SUCCESS;
	}

	@Override
	protected void beforeInput() throws Exception {
		if (id != null) {
			this.stockincomeBill = stockincomeBillService.get(id);
		}else {
			this.stockincomeBill = new StockincomeBill();
			stockincomeBill.setVdate(new Date());
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		if (id != null) {
			this.stockincomeBill = stockincomeBillService.get(id);
			stockincomeBill.setSupplier(null);
			stockincomeBill.setKeeper(null);
			stockincomeBill.setDepot(null);
			stockincomeBill.getItems().clear();
		} else {
			this.stockincomeBill = new StockincomeBill();
		}
	}

	@Override
	public StockincomeBill getModel() {
		return stockincomeBill;
	}

	public String auditor() throws Exception {
		String msg = stockincomeBillService.auditor(id, getLoginUser());
		
		putContext("map", msg);
		return JSON_TYPE;
	}
	
}
