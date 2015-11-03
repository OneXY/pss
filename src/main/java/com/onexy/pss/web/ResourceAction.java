package com.onexy.pss.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.onexy.pss.domain.Resource;
import com.onexy.pss.page.PageResult;
import com.onexy.pss.page.ResourceQuery;
import com.onexy.pss.service.IResourceService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class ResourceAction extends CRUDAction<Resource> {
	private String name;

	private Resource resource;

	private ResourceQuery baseQuery = new ResourceQuery();

	private PageResult<Resource> pageResult;

	private IResourceService resourceService;

	public void setName(String name) {
		this.name = name;
	}

	public PageResult<Resource> getPageResult() {
		return pageResult;
	}

	public ResourceQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(ResourceQuery baseQuery) {
		logger.info("正在注入:baseQuery...");
		this.baseQuery = baseQuery;
	}

	public void setResourceService(IResourceService resourceService) {
		logger.info("正在注入:resourceService...");
		this.resourceService = resourceService;
	}

	@Override
	public String input() throws Exception {
//		putContext("depts", departmentService.getAll());
		return INPUT;
	}

	public void validateSave() {
		if (StringUtils.isBlank(resource.getName())) {
			addFieldError("name", "用户名必须填写");
		}
	}

	@Override
	@InputConfig(methodName = "input")
	public String save() throws Exception {
		try {
//			Department department = resource.getDepartment();
//			if (department != null && department.getId() == -1) {
//				resource.setDepartment(null);
//			}
			if (id != null) {
				resourceService.update(resource);
				addActionMessage("修改成功");
			} else {
				resourceService.save(resource);
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
				resourceService.delete(id);
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
		// resourceService.delete(id);
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
		if (id != null && resourceService.get(id).getName().equals(name)) {
			out.print(true);
		} else {
			out.print(resourceService.findByName(name));
		}
	}

	@Override
	protected String list() throws Exception {
		pageResult = resourceService.findPageResult(baseQuery);
//		putContext("depts", departmentService.getAll());
		return SUCCESS;
	}

	@Override
	protected void beforeInput() throws Exception {
		if (id != null) {
			this.resource = resourceService.get(id);
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		if (id != null) {
			this.resource = resourceService.get(id);
//			resource.setDepartment(null);
		} else {
			this.resource = new Resource();
		}
	}

	@Override
	public Resource getModel() {
		return resource;
	}

}
