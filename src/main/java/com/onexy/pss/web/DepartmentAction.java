package com.onexy.pss.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.onexy.pss.domain.Department;
import com.onexy.pss.page.DepartmentQuery;
import com.onexy.pss.page.PageResult;
import com.onexy.pss.service.IDepartmentService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class DepartmentAction extends CRUDAction<Department> {
	private String name;

	private Department department;

	private DepartmentQuery baseQuery = new DepartmentQuery();

	private PageResult<Department> pageResult;

	private IDepartmentService departmentService;


	public void setName(String name) {
		this.name = name;
	}

	public PageResult<Department> getPageResult() {
		return pageResult;
	}

	public DepartmentQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(DepartmentQuery baseQuery) {
		logger.info("正在注入:baseQuery...");
		this.baseQuery = baseQuery;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		logger.info("正在注入:departmentService...");
		this.departmentService = departmentService;
	}

	@Override
	public String input() throws Exception {
//		putContext("depts", departmentService.getAll());
		return INPUT;
	}

	public void validateSave() {
		if (StringUtils.isBlank(department.getName())) {
			addFieldError("name", "用户名必须填写");
		}
	}

	@Override
	@InputConfig(methodName = "input")
	public String save() throws Exception {
		try {
//			Department department = department.getDepartment();
//			if (department != null && department.getId() == -1) {
//				department.setDepartment(null);
//			}
			if (id != null) {
				departmentService.update(department);
				addActionMessage("修改成功");
			} else {
				departmentService.save(department);
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
				departmentService.delete(id);
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
		// departmentService.delete(id);
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
		if (id != null && departmentService.get(id).getName().equals(name)) {
			out.print(true);
		} else {
			out.print(departmentService.findByName(name));
		}
	}

	@Override
	protected String list() throws Exception {
		pageResult = departmentService.findPageResult(baseQuery);
//		putContext("depts", departmentService.getAll());
		return SUCCESS;
	}

	@Override
	protected void beforeInput() throws Exception {
		if (id != null) {
			this.department = departmentService.get(id);
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		if (id != null) {
			this.department = departmentService.get(id);
//			department.setDepartment(null);
		} else {
			this.department = new Department();
		}
	}

	@Override
	public Department getModel() {
		return department;
	}

}
