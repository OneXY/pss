package com.onexy.pss.page;

import org.apache.commons.lang3.StringUtils;

import com.onexy.pss.domain.Resource;

public class ResourceQuery extends BaseQuery {
	private String name;
	
	private String method;

	public ResourceQuery() {
		super(Resource.class);
	}

	@Override
	protected void addWhere() {
		if (StringUtils.isNotBlank(name)) {
			addWhere("o.name LIKE ?", "%" + name + "%");
		}
		if (StringUtils.isNotBlank(method)) {
			addWhere("o.method LIKE ?", "%" + method + "%");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
}
