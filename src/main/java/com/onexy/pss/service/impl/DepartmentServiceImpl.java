package com.onexy.pss.service.impl;

import java.util.List;

import com.onexy.pss.domain.Department;
import com.onexy.pss.service.IDepartmentService;

public class DepartmentServiceImpl extends BaseServiceImpl<Department>
		implements IDepartmentService {
	@Override
	public boolean findByName(String name) {
		String hql = "select count(o) from Department o where o.name = ?";
		List<Long> result = baseDao.findByHql(hql, name);
		if (result.get(0).intValue() > 0) {
			logger.info("用户名已存在");
			return false;
		}
		logger.info("用户名不存在");
		return true;
	}

	@Override
	public Department findDeptByName(String name) {
		List<Department> list = baseDao.findByHql(
				"select o from Department o where o.name=?", name);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
