package com.onexy.pss.page;

import org.apache.commons.lang3.StringUtils;

import com.onexy.pss.domain.Menu;

public class MenuQuery extends BaseQuery {
	private String name;

	private String url;
	
	public MenuQuery() {
		super(Menu.class);
	}

	@Override
	protected void addWhere() {
		if (StringUtils.isNotBlank(name)) {
			addWhere("o.name LIKE ?", "%" + name + "%");
		}
		if (StringUtils.isNotBlank(url)) {
			addWhere("o.url LIKE ?", "%" + url + "%");
		}
//		if (deptId != null && deptId != -1L) {
//			addWhere("o.department.id = ?", deptId);
//		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
