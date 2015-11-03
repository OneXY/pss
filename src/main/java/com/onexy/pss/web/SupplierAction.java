package com.onexy.pss.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.onexy.pss.domain.Supplier;
import com.onexy.pss.page.SupplierQuery;
import com.onexy.pss.page.PageResult;
import com.onexy.pss.service.ISupplierService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class SupplierAction extends CRUDAction<Supplier> {
	private String name;

	private Supplier supplier;

	private SupplierQuery baseQuery = new SupplierQuery();

	private PageResult<Supplier> pageResult;

	private ISupplierService supplierService;


	public void setName(String name) {
		this.name = name;
	}

	public PageResult<Supplier> getPageResult() {
		return pageResult;
	}

	public SupplierQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(SupplierQuery baseQuery) {
		logger.info("正在注入:baseQuery...");
		this.baseQuery = baseQuery;
	}

	public void setSupplierService(ISupplierService supplierService) {
		logger.info("正在注入:supplierService...");
		this.supplierService = supplierService;
	}

	@Override
	public String input() throws Exception {
//		putContext("depts", departmentService.getAll());
		return INPUT;
	}

	public void validateSave() {
		if (StringUtils.isBlank(supplier.getName())) {
			addFieldError("name", "用户名必须填写");
		}
	}

	@Override
	@InputConfig(methodName = "input")
	public String save() throws Exception {
		try {
//			Department department = supplier.getDepartment();
//			if (department != null && department.getId() == -1) {
//				supplier.setDepartment(null);
//			}
			if (id != null) {
				supplierService.update(supplier);
				addActionMessage("修改成功");
			} else {
				supplierService.save(supplier);
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
			try {
				supplierService.delete(id);
				out.print("{\"success\":true,\"msg\":\"删除成功\"}");
			} catch (Exception e) {
				out.print("{\"success\":false,\"msg\":\"" + e.getMessage()
						+ "\"}");
			}
		} else {
			out.print("{\"success\":false,\"msg\":\"没有删除的"+id+"\"}");
		}
		// try {
		// if (id != null) {
		// supplierService.delete(id);
		// addActionMessage("删除成功");
		// } else {
		// addActionMessage("非法操作");
		// }
		// } catch (Exception e) {
		// addActionError("业务逻辑异常:" + e.getMessage());
		// return execute();
		// }
		return NONE;
	}

	@InputConfig(methodName = "input")
	public void checkName() throws Exception {
		logger.info("验证用户名为:" + name + "的用户是否存在");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		if (id != null && supplierService.get(id).getName().equals(name)) {
			out.print(true);
		} else {
			out.print(supplierService.findByName(name));
		}
	}

	@Override
	protected String list() throws Exception {
		pageResult = supplierService.findPageResult(baseQuery);
//		putContext("depts", departmentService.getAll());
		return SUCCESS;
	}

	@Override
	protected void beforeInput() throws Exception {
		if (id != null) {
			this.supplier = supplierService.get(id);
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		if (id != null) {
			this.supplier = supplierService.get(id);
//			supplier.setDepartment(null);
		} else {
			this.supplier = new Supplier();
		}
	}

	@Override
	public Supplier getModel() {
		return supplier;
	}

}
