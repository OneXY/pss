package com.onexy.pss.web;

import java.util.HashMap;

import com.onexy.pss.domain.ProductStock;
import com.onexy.pss.page.ProductStockQuery;
import com.onexy.pss.page.PageResult;
import com.onexy.pss.service.IProductStockService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class ProductStockAction extends CRUDAction<ProductStock> {
	private String name;

	private ProductStock productStock;

	private ProductStockQuery baseQuery = new ProductStockQuery();

	private PageResult<ProductStock> pageResult;

	private IProductStockService productStockService;


	public void setName(String name) {
		this.name = name;
	}

	public PageResult<ProductStock> getPageResult() {
		return pageResult;
	}

	public ProductStockQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(ProductStockQuery baseQuery) {
		logger.info("正在注入:baseQuery...");
		this.baseQuery = baseQuery;
	}

	public void setProductStockService(IProductStockService productStockService) {
		logger.info("正在注入:productStockService...");
		this.productStockService = productStockService;
	}

	@Override
	public String input() throws Exception {
//		putContext("depts", departmentService.getAll());
		return INPUT;
	}

	@Override
	@InputConfig(methodName = "input")
	public String save() throws Exception {
		try {
//			Department department = productStock.getDepartment();
//			if (department != null && department.getId() == -1) {
//				productStock.setDepartment(null);
//			}
			if (id != null) {
				productStockService.update(productStock);
				addActionMessage("修改成功");
			} else {
				productStockService.save(productStock);
				addActionMessage("添加成功");
				baseQuery.setCurrentPage(256);
			}
		} catch (Exception e) {
			addActionError("业务逻辑异常:" + e.getMessage());
			return input();
		}
		return RELOAD;
	}

	@Override
	public String delete() throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		if (id != null) {
			try {
				productStockService.delete(id);
				map.put("success", true);
				map.put("msg", "删除成功");
			} catch (Exception e) {
				map.put("msg", "异常:" + e.getMessage());
			}
		} else {
			map.put("msg", "没有删除的"+id);
		}
		putContext("map", map);
		return JSON_TYPE;
	}

	@Override
	protected String list() throws Exception {
		pageResult = productStockService.findPageResult(baseQuery);
//		putContext("depts", departmentService.getAll());
		return SUCCESS;
	}

	@Override
	protected void beforeInput() throws Exception {
		if (id != null) {
			this.productStock = productStockService.get(id);
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		if (id != null) {
			this.productStock = productStockService.get(id);
//			productStock.setDepartment(null);
		} else {
			this.productStock = new ProductStock();
		}
	}

	@Override
	public ProductStock getModel() {
		return productStock;
	}

}
