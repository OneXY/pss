package com.onexy.pss.service;

import java.util.List;

import com.onexy.pss.domain.StockincomeBillItem;
import com.onexy.pss.page.StockincomeBillItemQuery;

public interface IStockincomeBillItemService extends IBaseService<StockincomeBillItem> {
	
	List<Object[]> findByGroup(StockincomeBillItemQuery baseQuery);
	
	List<StockincomeBillItem> findItems(StockincomeBillItemQuery baseQuery, Object groupByValue);
	
	List<Object[]> findByGrop(StockincomeBillItemQuery baseQuery);
}
