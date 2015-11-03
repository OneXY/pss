package com.onexy.pss.service.impl;
import java.util.List;

import com.onexy.pss.domain.Menu;
import com.onexy.pss.service.IMenuService;


public class MenuServiceImpl extends BaseServiceImpl<Menu> implements IMenuService {
	@Override
	public boolean findByName(String name) {
		String hql = "select count(o) from Menu o where o.name = ?";
		List<Long> result = baseDao.findByHql(hql, name);
		if (result.get(0).intValue()>0) {
			logger.info("用户名已存在");
			return false;
		}
		logger.info("用户名不存在");
		return true;
	}

	@Override
	public List<Menu> findParentByLoginUser(Long userId) {
		String hql = "select distinct m from Employee e join e.roles r join r.menus m where e.id=? and m.parent is null";
		return baseDao.findByHql(hql, userId);
	}

	@Override
	public List<Menu> findChildrenByLoginUser(Long userId, Long parentId) {
		String hql = "select distinct m from Employee e join e.roles r join r.menus m where e.id=? and m.parent.id=?";
		return baseDao.findByHql(hql, userId,parentId);
	}
}
