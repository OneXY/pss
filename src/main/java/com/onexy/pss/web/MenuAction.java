package com.onexy.pss.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.onexy.pss.domain.Menu;
import com.onexy.pss.page.MenuQuery;
import com.onexy.pss.page.PageResult;
import com.onexy.pss.service.IMenuService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class MenuAction extends CRUDAction<Menu> {
	private String name;

	private Menu menu;

	private MenuQuery baseQuery = new MenuQuery();

	private PageResult<Menu> pageResult;

	private IMenuService menuService;


	public void setName(String name) {
		this.name = name;
	}

	public PageResult<Menu> getPageResult() {
		return pageResult;
	}

	public MenuQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(MenuQuery baseQuery) {
		logger.info("正在注入:baseQuery...");
		this.baseQuery = baseQuery;
	}

	public void setMenuService(IMenuService menuService) {
		logger.info("正在注入:menuService...");
		this.menuService = menuService;
	}

	@Override
	public String input() throws Exception {
//		putContext("depts", departmentService.getAll());
		return INPUT;
	}

	public void validateSave() {
		if (StringUtils.isBlank(menu.getName())) {
			addFieldError("name", "用户名必须填写");
		}
	}

	@Override
	@InputConfig(methodName = "input")
	public String save() throws Exception {
		try {
//			Department department = menu.getDepartment();
//			if (department != null && department.getId() == -1) {
//				menu.setDepartment(null);
//			}
			if (id != null) {
				menuService.update(menu);
				addActionMessage("修改成功");
			} else {
				menuService.save(menu);
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
				menuService.delete(id);
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
		// menuService.delete(id);
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
		if (id != null && menuService.get(id).getName().equals(name)) {
			out.print(true);
		} else {
			out.print(menuService.findByName(name));
		}
	}

	@Override
	protected String list() throws Exception {
		pageResult = menuService.findPageResult(baseQuery);
//		putContext("depts", departmentService.getAll());
		return SUCCESS;
	}

	@Override
	protected void beforeInput() throws Exception {
		if (id != null) {
			this.menu = menuService.get(id);
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		if (id != null) {
			this.menu = menuService.get(id);
//			menu.setDepartment(null);
		} else {
			this.menu = new Menu();
		}
	}

	@Override
	public Menu getModel() {
		return menu;
	}

}
