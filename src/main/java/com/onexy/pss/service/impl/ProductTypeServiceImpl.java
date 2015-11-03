package com.onexy.pss.service.impl;
import java.util.List;

import com.onexy.pss.domain.ProductType;
import com.onexy.pss.service.IProductTypeService;


public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType> implements IProductTypeService {
	@Override
	public boolean findByName(String name) {
		String hql = "select count(o) from ProductType o where o.name = ?";
		List<Long> result = baseDao.findByHql(hql, name);
		if (result.get(0).intValue()>0) {
			logger.info("用户名已存在");
			return false;
		}
		logger.info("用户名不存在");
		return true;
	}

	@Override
	public List<ProductType> getParents() {
		return baseDao.findByHql("select o from ProductType o where o.parent is null");
	}

	@Override
	public List<ProductType> findChildren(Long parentId) {
		return baseDao.findByHql("select o from ProductType o where o.parent.id=?", parentId);
	}
}
