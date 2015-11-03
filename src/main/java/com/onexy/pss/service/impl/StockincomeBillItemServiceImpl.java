package com.onexy.pss.service.impl;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.onexy.pss.domain.StockincomeBillItem;
import com.onexy.pss.page.StockincomeBillItemQuery;
import com.onexy.pss.service.IStockincomeBillItemService;


public class StockincomeBillItemServiceImpl extends BaseServiceImpl<StockincomeBillItem> implements IStockincomeBillItemService {

	@Override
	public List<Object[]> findByGroup(StockincomeBillItemQuery baseQuery) {
		String groupBy = baseQuery.getGroupBy();
		if (StringUtils.isBlank(groupBy)) {
			throw new RuntimeException("必须要有分组条件");
		}
		List paramList = baseQuery.getParamList();
		if (paramList.size() == 0) {
			String hql = "select "+groupBy+",count(o) from StockincomeBillItem o group by "+groupBy;
			return baseDao.findByHql(hql);
		}else {
			String where = baseQuery.getWhereHql();
			String hql = "select "+groupBy+",count(o) from StockincomeBillItem o "+where+" group by "+groupBy;
			return baseDao.findByHql(hql, paramList.toArray());
		}
	}

	@Override
	public List<StockincomeBillItem> findItems(
			StockincomeBillItemQuery baseQuery, Object groupByValue) {
		String groupBy = baseQuery.getGroupBy();
		List paramList = baseQuery.getParamList();
		if (paramList.size() == 0) {
			String hql = "select o from StockincomeBillItem o where "+groupBy+"=?";
			return baseDao.findByHql(hql,groupByValue);
		}else {
			String where = baseQuery.getWhereHql();
			String hql = "select o from StockincomeBillItem o "+where+" and "+groupBy+"=?";
			List list = new ArrayList(paramList);
			list.add(groupByValue);
			return baseDao.findByHql(hql, list.toArray());
		}
	}

	@Override
	public List<Object[]> findByGrop(StockincomeBillItemQuery baseQuery) {
		String groupBy = baseQuery.getGroupBy();
		if (StringUtils.isBlank(groupBy)) {
			throw new RuntimeException("必须要有分组条件");
		}
		List paramList = baseQuery.getParamList();
		if (paramList.size() == 0) {
			String hql = "select "+groupBy+",sum(o.amount) from StockincomeBillItem o group by "+groupBy;
			return baseDao.findByHql(hql);
		}else {
			String where = baseQuery.getWhereHql();
			String hql = "select "+groupBy+",sum(o.amount) from StockincomeBillItem o "+where+" group by "+groupBy;
			return baseDao.findByHql(hql, paramList.toArray());
		}
	}
}
