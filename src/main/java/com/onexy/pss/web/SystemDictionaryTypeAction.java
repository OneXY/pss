package com.onexy.pss.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.onexy.pss.domain.SystemDictionaryType;
import com.onexy.pss.page.SystemDictionaryTypeQuery;
import com.onexy.pss.page.PageResult;
import com.onexy.pss.service.ISystemDictionaryTypeService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class SystemDictionaryTypeAction extends CRUDAction<SystemDictionaryType> {
	private String name;

	private SystemDictionaryType systemDictionaryType;

	private SystemDictionaryTypeQuery baseQuery = new SystemDictionaryTypeQuery();

	private PageResult<SystemDictionaryType> pageResult;

	private ISystemDictionaryTypeService systemDictionaryTypeService;


	public void setName(String name) {
		this.name = name;
	}

	public PageResult<SystemDictionaryType> getPageResult() {
		return pageResult;
	}

	public SystemDictionaryTypeQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(SystemDictionaryTypeQuery baseQuery) {
		logger.info("正在注入:baseQuery...");
		this.baseQuery = baseQuery;
	}

	public void setSystemDictionaryTypeService(ISystemDictionaryTypeService systemDictionaryTypeService) {
		logger.info("正在注入:systemDictionaryTypeService...");
		this.systemDictionaryTypeService = systemDictionaryTypeService;
	}

	@Override
	public String input() throws Exception {
//		putContext("depts", departmentService.getAll());
		return INPUT;
	}

	public void validateSave() {
		if (StringUtils.isBlank(systemDictionaryType.getName())) {
			addFieldError("name", "用户名必须填写");
		}
	}

	@Override
	@InputConfig(methodName = "input")
	public String save() throws Exception {
		try {
//			Department department = systemDictionaryType.getDepartment();
//			if (department != null && department.getId() == -1) {
//				systemDictionaryType.setDepartment(null);
//			}
			if (id != null) {
				systemDictionaryTypeService.update(systemDictionaryType);
				addActionMessage("修改成功");
			} else {
				systemDictionaryTypeService.save(systemDictionaryType);
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
				systemDictionaryTypeService.delete(id);
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
		// systemDictionaryTypeService.delete(id);
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
		if (id != null && systemDictionaryTypeService.get(id).getName().equals(name)) {
			out.print(true);
		} else {
			out.print(systemDictionaryTypeService.findByName(name));
		}
	}

	@Override
	protected String list() throws Exception {
		pageResult = systemDictionaryTypeService.findPageResult(baseQuery);
//		putContext("depts", departmentService.getAll());
		return SUCCESS;
	}

	@Override
	protected void beforeInput() throws Exception {
		if (id != null) {
			this.systemDictionaryType = systemDictionaryTypeService.get(id);
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		if (id != null) {
			this.systemDictionaryType = systemDictionaryTypeService.get(id);
//			systemDictionaryType.setDepartment(null);
		} else {
			this.systemDictionaryType = new SystemDictionaryType();
		}
	}

	@Override
	public SystemDictionaryType getModel() {
		return systemDictionaryType;
	}

}
