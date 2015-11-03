package com.onexy.pss.web;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.onexy.pss.domain.Employee;
import com.onexy.pss.service.IEmployeeService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class LoginAction extends BaseAction {
	private String username;
	private String password;
	private String captcha;
	private IEmployeeService employeeService;

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	@Override
	public String execute() throws Exception {
		return LOGIN;
	}

	public void validateCheck() {
		if (StringUtils.isBlank(username)) {
			addFieldError("username", "用户名不能为空");
		} else if (StringUtils.isBlank(captcha)) {
			//addFieldError("captchaErrorMsg", "请输入验证码!");
		}
		Map<String, Object> session = ActionContext.getContext().getSession();
		String randomCode = (String) session.get("randomCode");
		if (!randomCode.equals(captcha)) {
			//addFieldError("captchaErrorMsg", "验证码不正确!");
		}
		session.remove("randomCode");
	}
	
	@InputConfig(resultName="login")
	public String check() throws Exception {
		Employee employee = employeeService.findForLogin(username, password);
		if (employee != null) {
			ActionContext.getContext().getSession()
					.put("USER_IN_SESSION", employee);
			return SUCCESS;
		}
		addActionMessage("登录失败");
		return LOGIN;
	}

}
