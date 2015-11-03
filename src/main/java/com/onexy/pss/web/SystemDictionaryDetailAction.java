package com.onexy.pss.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.onexy.pss.domain.SystemDictionaryDetail;
import com.onexy.pss.page.SystemDictionaryDetailQuery;
import com.onexy.pss.page.PageResult;
import com.onexy.pss.service.ISystemDictionaryDetailService;
import com.onexy.pss.service.ISystemDictionaryTypeService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class SystemDictionaryDetailAction extends CRUDAction<SystemDictionaryDetail> {
	private String name;

	private SystemDictionaryDetail systemDictionaryDetail;

	private SystemDictionaryDetailQuery baseQuery = new SystemDictionaryDetailQuery();

	private PageResult<SystemDictionaryDetail> pageResult;

	private ISystemDictionaryDetailService systemDictionaryDetailService;

	private ISystemDictionaryTypeService systemDictionaryTypeService;

	public void setName(String name) {
		this.name = name;
	}

	public PageResult<SystemDictionaryDetail> getPageResult() {
		return pageResult;
	}

	public SystemDictionaryDetailQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(SystemDictionaryDetailQuery baseQuery) {
		logger.info("正在注入:baseQuery...");
		this.baseQuery = baseQuery;
	}

	public void setSystemDictionaryDetailService(ISystemDictionaryDetailService systemDictionaryDetailService) {
		logger.info("正在注入:systemDictionaryDetailService...");
		this.systemDictionaryDetailService = systemDictionaryDetailService;
	}
	
	public void setSystemDictionaryTypeService(
			ISystemDictionaryTypeService systemDictionaryTypeService) {
		this.systemDictionaryTypeService = systemDictionaryTypeService;
	}

	@Override
	public String input() throws Exception {
		putContext("allTypes", systemDictionaryTypeService.getAll());
		return INPUT;
	}

	public void validateSave() {
		if (StringUtils.isBlank(systemDictionaryDetail.getName())) {
			addFieldError("name", "用户名必须填写");
		}
	}

	@Override
	@InputConfig(methodName = "input")
	public String save() throws Exception {
		try {
//			Department department = systemDictionaryDetail.getDepartment();
//			if (department != null && department.getId() == -1) {
//				systemDictionaryDetail.setDepartment(null);
//			}
			if (id != null) {
				systemDictionaryDetailService.update(systemDictionaryDetail);
				addActionMessage("修改成功");
			} else {
				systemDictionaryDetailService.save(systemDictionaryDetail);
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
				systemDictionaryDetailService.delete(id);
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
		// systemDictionaryDetailService.delete(id);
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
		if (id != null && systemDictionaryDetailService.get(id).getName().equals(name)) {
			out.print(true);
		} else {
			out.print(systemDictionaryDetailService.findByName(name));
		}
	}

	@Override
	protected String list() throws Exception {
		pageResult = systemDictionaryDetailService.findPageResult(baseQuery);
		putContext("allTypes", systemDictionaryTypeService.getAll());
		return SUCCESS;
	}

	@Override
	protected void beforeInput() throws Exception {
		if (id != null) {
			this.systemDictionaryDetail = systemDictionaryDetailService.get(id);
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		if (id != null) {
			this.systemDictionaryDetail = systemDictionaryDetailService.get(id);
//			systemDictionaryDetail.setDepartment(null);
		} else {
			this.systemDictionaryDetail = new SystemDictionaryDetail();
		}
	}

	@Override
	public SystemDictionaryDetail getModel() {
		return systemDictionaryDetail;
	}

}
