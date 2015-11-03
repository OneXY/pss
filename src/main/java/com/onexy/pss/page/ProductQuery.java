package com.onexy.pss.page;

import org.apache.commons.lang3.StringUtils;

import com.onexy.pss.domain.Product;

public class ProductQuery extends BaseQuery {
	private String name;

	private String color;
	
	public ProductQuery() {
		super(Product.class);
	}

	@Override
	protected void addWhere() {
		if (StringUtils.isNotBlank(name)) {
			addWhere("o.name LIKE ?", "%" + name + "%");
		}
		if (StringUtils.isNotBlank(color)) {
			addWhere("o.color LIKE ?", color);
		}
//		if (deptId != null && deptId != -1L) {
//			addWhere("o.department.id = ?", deptId);
//		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
