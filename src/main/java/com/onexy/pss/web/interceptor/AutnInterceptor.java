package com.onexy.pss.web.interceptor;

import java.util.Arrays;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onexy.pss.domain.Employee;
import com.onexy.pss.service.IEmployeeService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AutnInterceptor extends AbstractInterceptor {
	private List<String> excludeActions;
	private IEmployeeService employeeService;
	private Logger logger = LoggerFactory.getLogger(getClass());

	public AutnInterceptor() {
		logger.info("正在创建拦截器AutnInterceptor...");
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		logger.info("AutnInterceptor:正在访问:"
				+ invocation.getAction().getClass().getName());
		if (excludeActions.contains(invocation.getAction().getClass()
				.getSimpleName())) {
			return invocation.invoke();
		}

		Employee employee = (Employee) ServletActionContext.getRequest()
				.getSession().getAttribute("USER_IN_SESSION");
		if (employee == null) {
			return Action.LOGIN;
		}

		String className = invocation.getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String classMethodName = className + "." + methodName;
		String allClassMethodName = className + ".ALL";
		List<String> allResourceMethods = employeeService
				.getAllResourceMethods();
		if (allResourceMethods.contains(classMethodName)
				|| allResourceMethods.contains(allClassMethodName)) {
			List<String> loginUserMethods = employeeService.findMethodsByLoginUser(employee.getId());
			if (loginUserMethods.contains(classMethodName)
				|| loginUserMethods.contains(allClassMethodName)) {
				logger.info("有权限");
			} else {
				return "auth";
			}
		} else {
			logger.info("不需要拦截");
		}
		return invocation.invoke();
	}

	public void setExcludeActions(String actions) {
		logger.info("为拦截器AutnInterceptor设置参数:" + actions);
		excludeActions = Arrays.asList(actions.split(","));
	}

}
