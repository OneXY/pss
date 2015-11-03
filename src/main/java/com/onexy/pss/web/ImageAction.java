package com.onexy.pss.web;

import java.io.ByteArrayInputStream;
import com.onexy.pss.utils.SecurityCode;
import com.onexy.pss.utils.SecurityImage;
import com.opensymphony.xwork2.ActionContext;

public class ImageAction extends BaseAction {
	private ByteArrayInputStream imageStream = null;
	
	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}
	
	@Override
	public String execute() throws Exception {
		String securityCode = SecurityCode.getSecurityCode();
		ActionContext.getContext().getSession().put("randomCode", securityCode);
		imageStream = SecurityImage.getImageAsInputStream(securityCode);
		return SUCCESS;
	}
}
