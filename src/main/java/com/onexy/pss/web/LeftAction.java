package com.onexy.pss.web;

import java.util.List;

import com.onexy.pss.domain.Employee;
import com.onexy.pss.domain.Menu;
import com.onexy.pss.service.IMenuService;

public class LeftAction extends BaseAction {
	private Long id;

	private IMenuService menuService;

	public void setId(Long id) {
		this.id = id;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	// @Override
	// public String execute() throws Exception {
	// HttpServletResponse response = ServletActionContext.getResponse();
	// response.setContentType("text/json; charset=utf-8");
	// PrintWriter out = response.getWriter();
	// out.print("{\"id\":1,\"name\":\"abc\",\"isParent\":true}");
	// out.close();
	// return NONE;
	// }

	@Override
	public String execute() throws Exception {
		List<Menu> menus = null;
		Employee loginUser = getLoginUser();
		if (id == null) {
			menus = menuService.findParentByLoginUser(loginUser.getId());
		} else {
			menus = menuService.findChildrenByLoginUser(loginUser.getId(), id);
		}
		putContext("map", menus);
		return JSON_TYPE;
	}
}
