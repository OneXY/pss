package com.onexy.pss.page;

import org.apache.commons.lang3.StringUtils;

import com.onexy.pss.domain.Employee;

public class EmployeeQuery extends BaseQuery {
	private String name;
	private String email;
	private Long deptId;

	public EmployeeQuery() {
		super(Employee.class);
	}

	@Override
	protected void addWhere() {
		if (StringUtils.isNotBlank(name)) {
			addWhere("o.name LIKE ?", "%" + name + "%");
		}
		if (StringUtils.isNotBlank(email)) {
			addWhere("o.email LIKE ?", "%" + email + "%");
		}
		if (deptId != null && deptId != -1L) {
			addWhere("o.department.id = ?", deptId);
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

}
