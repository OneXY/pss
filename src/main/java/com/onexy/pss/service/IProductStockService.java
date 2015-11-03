package com.onexy.pss.service;

import com.onexy.pss.domain.ProductStock;

public interface IProductStockService extends IBaseService<ProductStock> {
	boolean findByName(String name);
}
