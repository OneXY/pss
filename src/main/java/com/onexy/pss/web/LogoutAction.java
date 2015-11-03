package com.onexy.pss.web;

import org.apache.struts2.ServletActionContext;

public class LogoutAction extends BaseAction{
	@Override
	public String execute() throws Exception {
		ServletActionContext.getRequest().getSession().invalidate();
		return LOGIN;
	}
}
