package com.onexy.pss.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.onexy.pss.domain.PurchaseBillItem;
import com.onexy.pss.page.PurchaseBillItemQuery;
import com.onexy.pss.service.IPurchaseBillItemService;

public class PurchaseBillItemAction extends BaseAction {
	private PurchaseBillItemQuery baseQuery = new PurchaseBillItemQuery();

	private IPurchaseBillItemService purchaseBillItemService;

	public PurchaseBillItemQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(PurchaseBillItemQuery baseQuery) {
		logger.info("正在注入:baseQuery...");
		this.baseQuery = baseQuery;
	}

	public void setPurchaseBillItemService(IPurchaseBillItemService purchaseBillItemService) {
		logger.info("正在注入:purchaseBillItemService...");
		this.purchaseBillItemService = purchaseBillItemService;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String query() throws Exception {
		List<Object[]> list = purchaseBillItemService.findByGroup(baseQuery);
		putContext("list", list);
		return SUCCESS;
	}
	
	//FusionCharts
	public String chart1() throws Exception {
		return "chart1";
	} 
	public String chart2() throws Exception {
		HashMap<String, Object> rootMap = new HashMap<String, Object>();
		HashMap<String, String> chart = new HashMap<String, String>();
		chart.put("caption", "采购报表");
		chart.put("xaxisname", "供应商");
		chart.put("yaxisname", "金额");
		chart.put("numberprefix","￥");
		rootMap.put("chart", chart);
		
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		List<Object[]> list = purchaseBillItemService.findByGrop(baseQuery);
		for (Object[] objects : list) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("label", objects[0].toString());
			map.put("value", objects[1].toString());
			data.add(map);
		}
		rootMap.put("data", data);
		
		putContext("map", rootMap);
		return JSON_TYPE;
	} 
	//HighCharts
	public String chart3() throws Exception {
		return "chart3";
	} 
	/**
	 * data: [{
	 * 	           name: 'Chrome',
	 * 	           y: 12.8,
	 * 	           sliced: true,
	 * 	           selected: true
	 * 	       }]
	 * @return
	 * @throws Exception
	 */
	public String chart4() throws Exception {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		List<Object[]> list = purchaseBillItemService.findByGrop(baseQuery);
		for (Object[] objects : list) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", objects[0].toString());
			map.put("y", objects[1]);
			map.put("sliced", true);
			map.put("selected", true);
			data.add(map);
		}
		
		putContext("map", data);
		return JSON_TYPE;
	} 
	
	public List<PurchaseBillItem> findItems(Object groupByValue){
		List<PurchaseBillItem> list = purchaseBillItemService.findItems(baseQuery, groupByValue);
		return list;
	}
	
}
