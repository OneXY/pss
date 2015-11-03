package com.onexy.pss.service;

import java.util.List;

import com.onexy.pss.domain.PurchaseBillItem;
import com.onexy.pss.page.PurchaseBillItemQuery;

public interface IPurchaseBillItemService extends IBaseService<PurchaseBillItem> {
	List<Object[]> findByGroup(PurchaseBillItemQuery baseQuery);
	
	List<PurchaseBillItem> findItems(PurchaseBillItemQuery baseQuery, Object groupByValue);
	
	List<Object[]> findByGrop(PurchaseBillItemQuery baseQuery);
}
