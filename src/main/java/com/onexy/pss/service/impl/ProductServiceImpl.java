package com.onexy.pss.service.impl;
import java.io.File;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import com.onexy.pss.domain.Product;
import com.onexy.pss.service.IProductService;


public class ProductServiceImpl extends BaseServiceImpl<Product> implements IProductService {
	@Override
	public boolean findByName(String name) {
		String hql = "select count(o) from Product o where o.name = ?";
		List<Long> result = baseDao.findByHql(hql, name);
		if (result.get(0).intValue()>0) {
			logger.info("用户名已存在");
			return false;
		}
		logger.info("用户名不存在");
		return true;
	}
	

	@Override
	public void deleteImage(String webapp,Product product) {
		if (StringUtils.isNotBlank(product.getPic())) {
			File deleteFile = new File(webapp, product.getPic());
			File deleteFile2 = new File(webapp, product.getSmallPic());
			if (deleteFile.exists()) {
				deleteFile.delete();
			}
			if (deleteFile2.exists()) {
				deleteFile2.delete();
			}
		}
	}
}
