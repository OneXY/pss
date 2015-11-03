package com.onexy.pss.service.impl;
import java.util.List;

import com.onexy.pss.domain.Supplier;
import com.onexy.pss.service.ISupplierService;


public class SupplierServiceImpl extends BaseServiceImpl<Supplier> implements ISupplierService {
	@Override
	public boolean findByName(String name) {
		String hql = "select count(o) from Supplier o where o.name = ?";
		List<Long> result = baseDao.findByHql(hql, name);
		if (result.get(0).intValue()>0) {
			logger.info("用户名已存在");
			return false;
		}
		logger.info("用户名不存在");
		return true;
	}
}
