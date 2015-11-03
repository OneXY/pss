package com.onexy.pss.page;

import org.apache.commons.lang3.StringUtils;

import com.onexy.pss.domain.Depot;

public class DepotQuery extends BaseQuery {
	private String name;

	public DepotQuery() {
		super(Depot.class);
	}

	@Override
	protected void addWhere() {
		if (StringUtils.isNotBlank(name)) {
			addWhere("o.name LIKE ?", "%" + name + "%");
		}
//		if (StringUtils.isNotBlank(email)) {
//			addWhere("o.email LIKE ?", "%" + email + "%");
//		}
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

}
