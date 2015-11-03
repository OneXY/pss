package com.onexy.pss.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onexy.pss.domain.Employee;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {
	public static final String RELOAD = "reload";
	
	public static final String JSON_TYPE = "jsonType";
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected void putContext(String key, Object value) {
		ActionContext.getContext().put(key, value);
	}
	
	protected Employee getLoginUser() {
		return (Employee)ActionContext.getContext().getSession().get("USER_IN_SESSION");
	}
	
}
