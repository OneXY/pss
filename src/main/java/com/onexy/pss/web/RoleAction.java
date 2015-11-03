package com.onexy.pss.web;

import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.onexy.pss.domain.Resource;
import com.onexy.pss.domain.Role;
import com.onexy.pss.page.PageResult;
import com.onexy.pss.page.RoleQuery;
import com.onexy.pss.service.IResourceService;
import com.onexy.pss.service.IRoleService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class RoleAction extends CRUDAction<Role> {
	private String name;
	
	private Long[] ids;

	private Role role;

	private RoleQuery baseQuery = new RoleQuery();

	private PageResult<Role> pageResult;

	private IRoleService roleService;
	
	private IResourceService resourceService;
	
	public RoleAction() {
		logger.info("正在创建:RoleAction...");
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public PageResult<Role> getPageResult() {
		return pageResult;
	}

	public RoleQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(RoleQuery baseQuery) {
		logger.info("正在注入:baseQuery...");
		this.baseQuery = baseQuery;
	}

	public void setRoleService(IRoleService roleService) {
		logger.info("正在注入:roleService...");
		this.roleService = roleService;
	}

	public void setResourceService(IResourceService resourceService) {
		logger.info("正在注入:resourceService...");
		this.resourceService = resourceService;
	}
	
	@Override
	public String input() throws Exception {
		putContext("allRoles", resourceService.getAll());
		return INPUT;
	}

	public void validateSave() {
		if (StringUtils.isBlank(role.getName())) {
			addFieldError("name", "用户名必须填写");
		}
	}

	@Override
	@InputConfig(methodName = "input")
	public String save() throws Exception {
		try {
			if (ids != null) {
				for (Long id : ids) {
					role.getResources().add(new Resource(id));
				}
			}
			
			if (id != null) {
				roleService.update(role);
				addActionMessage("修改成功");
			} else {
				roleService.save(role);
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
				roleService.delete(id);
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
		// roleService.delete(id);
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
		if (id != null && roleService.get(id).getName().equals(name)) {
			out.print(true);
		} else {
			out.print(roleService.findByName(name));
		}
	}

	@Override
	protected String list() throws Exception {
		pageResult = roleService.findPageResult(baseQuery);
		return SUCCESS;
	}

	@Override
	protected void beforeInput() throws Exception {
		if (id != null) {
			this.role = roleService.get(id);
			Set<Resource> resources = role.getResources();
			ids = new Long[resources.size()];
			int index = 0;
			for (Resource resource : resources) {
				ids[index++] = resource.getId();
			}
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		if (id != null) {
			this.role = roleService.get(id);
//			role.setDepartment(null);
			role.getResources().clear();
		} else {
			this.role = new Role();
		}
	}

	@Override
	public Role getModel() {
		return role;
	}

}
