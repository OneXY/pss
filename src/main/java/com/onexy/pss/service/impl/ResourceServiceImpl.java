package com.onexy.pss.service.impl;
import java.util.List;

import com.onexy.pss.domain.Resource;
import com.onexy.pss.service.IResourceService;


public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements IResourceService {
	@Override
	public boolean findByName(String name) {
		String hql = "select count(o) from Resource o where o.name = ?";
		List<Long> result = baseDao.findByHql(hql, name);
		if (result.get(0).intValue()>0) {
			logger.info("用户名已存在");
			return false;
		}
		logger.info("用户名不存在");
		return true;
	}
}
