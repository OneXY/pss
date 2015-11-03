package com.onexy.pss.web;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.onexy.pss.domain.Department;
import com.onexy.pss.domain.Employee;
import com.onexy.pss.domain.Role;
import com.onexy.pss.page.EmployeeQuery;
import com.onexy.pss.page.PageResult;
import com.onexy.pss.service.IDepartmentService;
import com.onexy.pss.service.IEmployeeService;
import com.onexy.pss.service.IRoleService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class EmployeeAction extends CRUDAction<Employee> {
	private String name;

	private Long[] ids;
	
	private Employee employee;

	private EmployeeQuery baseQuery = new EmployeeQuery();

	private PageResult<Employee> pageResult;

	private IEmployeeService employeeService;

	private IDepartmentService departmentService;
	
	private IRoleService roleService;
	
	public void setName(String name) {
		this.name = name;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public PageResult<Employee> getPageResult() {
		return pageResult;
	}

	public EmployeeQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(EmployeeQuery baseQuery) {
		logger.info("正在注入:baseQuery...");
		this.baseQuery = baseQuery;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		logger.info("正在注入:employeeService...");
		this.employeeService = employeeService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		logger.info("正在注入:departmentService...");
		this.departmentService = departmentService;
	}

	public void setRoleService(IRoleService roleService) {
		logger.info("正在注入:roleService...");
		this.roleService = roleService;
	}
	
	@Override
	public String input() throws Exception {
		putContext("depts", departmentService.getAll());
		putContext("allRoles", roleService.getAll());
		return INPUT;
	}

	public void validateSave() {
		if (StringUtils.isBlank(employee.getName())) {
			addFieldError("name", "用户名必须填写");
		}
	}

	@Override
	@InputConfig(methodName = "input")
	public String save() throws Exception {
		try {
			Department department = employee.getDepartment();
			if (department != null && department.getId() == -1) {
				employee.setDepartment(null);
			}
			
			for (Long id : ids) {
				employee.getRoles().add(new Role(id));
			}
			
			if (id != null) {
				employeeService.update(employee);
				addActionMessage("修改成功");
			} else {
				employeeService.save(employee);
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
				employeeService.delete(id);
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
		// employeeService.delete(id);
		// addActionMessage("删除成功");
		// } else {
		// addActionMessage("非法操作");
		// }
		// } catch (Exception e) {
		// addActionError("业务逻辑异常:" + e.getMessage());
		// return execute();
		// }
		out.close();
		return NONE;
	}

	@InputConfig(methodName = "input")
	public void checkName() throws Exception {
		logger.info("验证用户名为:" + name + "的用户是否存在");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		if (id != null && employeeService.get(id).getName().equals(name)) {
			out.print(true);
		} else {
			out.print(employeeService.findByName(name));
		}
	}

	@Override
	protected String list() throws Exception {
		pageResult = employeeService.findPageResult(baseQuery);
		putContext("depts", departmentService.getAll());
		return SUCCESS;
	}

	@Override
	protected void beforeInput() throws Exception {
		if (id != null) {
			this.employee = employeeService.get(id);
			Set<Role> roles = employee.getRoles();
			ids = new Long[roles.size()];
			int index = 0;
			for (Role role : roles) {
				ids[index++] = role.getId();
			}
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		if (id != null) {
			this.employee = employeeService.get(id);
			employee.setDepartment(null);
			employee.getRoles().clear();
		} else {
			this.employee = new Employee();
		}
	}

	@Override
	public Employee getModel() {
		return employee;
	}
	
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}
	
	public String download() throws Exception{
		String[] heads = {"编号","用户名","年龄","邮箱","部门名称"};
		baseQuery.setPageSize(256);
		List<String[]> list = new ArrayList<String[]>();
		List<Employee> employees = employeeService.findPageResult(baseQuery).getRows();
		for (Employee employee : employees) {
			String[] strs = new String[heads.length];
			strs[0] = employee.getId().toString();
			strs[1] = employee.getName().toString();
			strs[2] = employee.getAge().toString();
			strs[3] = employee.getEmail();
			strs[4] = employee.getDepartment().getName();
			list.add(strs);
		}
		this.inputStream = employeeService.download(heads, list);
		return "download";
	}
	
	public String getFileName() throws UnsupportedEncodingException {
		return new String("员工列表.xls".getBytes("GBK"),"ISO8859-1");
	}

}
