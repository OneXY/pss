package com.onexy.pss.service.impl;
import java.util.List;

import com.onexy.pss.domain.SystemDictionaryDetail;
import com.onexy.pss.domain.SystemDictionaryType;
import com.onexy.pss.service.ISystemDictionaryDetailService;


public class SystemDictionaryDetailServiceImpl extends BaseServiceImpl<SystemDictionaryDetail> implements ISystemDictionaryDetailService {
	@Override
	public boolean findByName(String name) {
		String hql = "select count(o) from SystemDictionaryDetail o where o.name = ?";
		List<Long> result = baseDao.findByHql(hql, name);
		if (result.get(0).intValue()>0) {
			logger.info("用户名已存在");
			return false;
		}
		logger.info("用户名不存在");
		return true;
	}

	String hql = "select o from SystemDictionaryDetail o where o.types.sn=?";
	@Override
	public List<SystemDictionaryDetail> getBrands() {
		return baseDao.findByHql(hql, SystemDictionaryType.PRODUCT_BRAND);
	}

	@Override
	public List<SystemDictionaryDetail> getUnits() {
		return baseDao.findByHql(hql, SystemDictionaryType.PRODUCT_UNIT);
	}
}
