package com.onexy.pss.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.onexy.pss.domain.PurchaseBillItem;
import com.onexy.pss.page.PurchaseBillItemQuery;
import com.onexy.pss.service.IPurchaseBillItemService;


public class PurchaseBillItemServiceImpl extends BaseServiceImpl<PurchaseBillItem> implements IPurchaseBillItemService {
	@Override
	public List<Object[]> findByGroup(PurchaseBillItemQuery baseQuery) {
		String groupBy = baseQuery.getGroupBy();
		if (StringUtils.isBlank(groupBy)) {
			throw new RuntimeException("必须要有分组条件");
		}
		List paramList = baseQuery.getParamList();
		if (paramList.size() == 0) {
			String hql = "select "+groupBy+",count(o) from PurchaseBillItem o group by "+groupBy;
			return baseDao.findByHql(hql);
		}else {
			String where = baseQuery.getWhereHql();
			String hql = "select "+groupBy+",count(o) from PurchaseBillItem o "+where+" group by "+groupBy;
			return baseDao.findByHql(hql, paramList.toArray());
		}
	}

	@Override
	public List<PurchaseBillItem> findItems(PurchaseBillItemQuery baseQuery,
			Object groupByValue) {
		String groupBy = baseQuery.getGroupBy();
		List paramList = baseQuery.getParamList();
		if (paramList.size() == 0) {
			String hql = "select o from PurchaseBillItem o where "+groupBy+"=?";
			return baseDao.findByHql(hql,groupByValue);
		}else {
			String where = baseQuery.getWhereHql();
			String hql = "select o from PurchaseBillItem o "+where+" and "+groupBy+"=?";
			List list = new ArrayList(paramList);
			list.add(groupByValue);
			return baseDao.findByHql(hql, list.toArray());
		}
	}
	
	@Override
	public List<Object[]> findByGrop(PurchaseBillItemQuery baseQuery) {
		String groupBy = baseQuery.getGroupBy();
		if (StringUtils.isBlank(groupBy)) {
			throw new RuntimeException("必须要有分组条件");
		}
		List paramList = baseQuery.getParamList();
		if (paramList.size() == 0) {
			String hql = "select "+groupBy+",sum(o.amount) from PurchaseBillItem o group by "+groupBy;
			return baseDao.findByHql(hql);
		}else {
			String where = baseQuery.getWhereHql();
			String hql = "select "+groupBy+",sum(o.amount) from PurchaseBillItem o "+where+" group by "+groupBy;
			return baseDao.findByHql(hql, paramList.toArray());
		}
	}
	
}
