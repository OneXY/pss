package com.onexy.pss.web;

import org.apache.struts2.ServletActionContext;

import com.onexy.pss.domain.Employee;
import com.onexy.pss.service.IEmployeeService;

public class UpdatePasswordAction extends BaseAction {
	private String oldPassword;
	private String newPassword;
	private IEmployeeService employeeService;

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String update() throws Exception {
		Employee employee = (Employee) ServletActionContext.getRequest()
				.getSession().getAttribute("USER_IN_SESSION");// 持久还是游离状态？游离状态
		if (employee.getPassword().equals(oldPassword)) {// 旧密码输入正确
			employee.setPassword(newPassword);
			employeeService.update(employee);
			// 销毁HttpSession里面的登录用户
			ServletActionContext.getRequest().getSession()
					.removeAttribute("USER_IN_SESSION");
			return LOGIN;
		}
		addActionError("旧密码输入错误");
		return SUCCESS;
	}
}
