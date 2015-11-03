package com.onexy.pss.service.impl;

import java.util.Date;
import java.util.List;

import com.onexy.pss.dao.BaseDao;
import com.onexy.pss.domain.ProductStock;
import com.onexy.pss.service.IQuartzJobService;

public class QuartzJobServiceImpl implements IQuartzJobService {
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public void work() {
		System.err.println(new Date().toLocaleString());
		String hql = "select o from ProductStock o where (o.num>=o.topNum or o.num<=o.bottomNum) and o.warning=?";
		List<ProductStock> list = baseDao.findByHql(hql, true);
		for (ProductStock productStock : list) {
			System.err.println("发出库存预警..."+productStock);
		}
	}

}
