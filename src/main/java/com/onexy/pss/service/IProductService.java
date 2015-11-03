package com.onexy.pss.service;

import com.onexy.pss.domain.Product;

public interface IProductService extends IBaseService<Product> {
	boolean findByName(String name);
	public void deleteImage(String webapp,Product product);
}
