package com.onexy.pss.service;

import java.util.List;

import com.onexy.pss.domain.Menu;

public interface IMenuService extends IBaseService<Menu> {
	boolean findByName(String name);
	List<Menu> findParentByLoginUser(Long userId);
	List<Menu> findChildrenByLoginUser(Long userId,Long parentId);
}
