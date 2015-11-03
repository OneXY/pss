package com.onexy.pss.service;

import java.util.List;

import com.onexy.pss.domain.ProductType;

public interface IProductTypeService extends IBaseService<ProductType> {
	boolean findByName(String name);

	List<ProductType> getParents();
	
	List<ProductType> findChildren(Long parentId);
}
