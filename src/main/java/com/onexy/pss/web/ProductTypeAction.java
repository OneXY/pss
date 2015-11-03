package com.onexy.pss.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.onexy.pss.domain.ProductType;
import com.onexy.pss.page.ProductTypeQuery;
import com.onexy.pss.page.PageResult;
import com.onexy.pss.service.IProductTypeService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class ProductTypeAction extends CRUDAction<ProductType> {
	private String name;

	private ProductType productType;

	private ProductTypeQuery baseQuery = new ProductTypeQuery();

	private PageResult<ProductType> pageResult;

	private IProductTypeService productTypeService;


	public void setName(String name) {
		this.name = name;
	}

	public PageResult<ProductType> getPageResult() {
		return pageResult;
	}

	public ProductTypeQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(ProductTypeQuery baseQuery) {
		logger.info("正在注入:baseQuery...");
		this.baseQuery = baseQuery;
	}

	public void setProductTypeService(IProductTypeService productTypeService) {
		logger.info("正在注入:productTypeService...");
		this.productTypeService = productTypeService;
	}

	@Override
	public String input() throws Exception {
		putContext("allParentTypes", productTypeService.getParents());
		return INPUT;
	}

	public void validateSave() {
		if (StringUtils.isBlank(productType.getName())) {
			addFieldError("name", "用户名必须填写");
		}
	}

	@Override
	@InputConfig(methodName = "input")
	public String save() throws Exception {
		try {
			ProductType parentType = productType.getParent();
			if (parentType != null && parentType.getId() == -1) {
				productType.setParent(null);
			}
			if (id != null) {
				productTypeService.update(productType);
				addActionMessage("修改成功");
			} else {
				productTypeService.save(productType);
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
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (id != null) {
			try {
				productTypeService.delete(id);
				out.print("{\"success\":true,\"msg\":\"删除成功\"}");
			} catch (Exception e) {
				out.print("{\"success\":false,\"msg\":\"" + e.getMessage()
						+ "\"}");
			}
		} else {
			out.print("{\"success\":false,\"msg\":\"没有删除的"+id+"\"}");
		}
		// try {
		// if (id != null) {
		// productTypeService.delete(id);
		// addActionMessage("删除成功");
		// } else {
		// addActionMessage("非法操作");
		// }
		// } catch (Exception e) {
		// addActionError("业务逻辑异常:" + e.getMessage());
		// return execute();
		// }
		return NONE;
	}

	@InputConfig(methodName = "input")
	public void checkName() throws Exception {
		logger.info("验证用户名为:" + name + "的用户是否存在");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		if (id != null && productTypeService.get(id).getName().equals(name)) {
			out.print(true);
		} else {
			out.print(productTypeService.findByName(name));
		}
	}

	@Override
	protected String list() throws Exception {
		pageResult = productTypeService.findPageResult(baseQuery);
//		putContext("depts", departmentService.getAll());
		return SUCCESS;
	}

	@Override
	protected void beforeInput() throws Exception {
		if (id != null) {
			this.productType = productTypeService.get(id);
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		if (id != null) {
			this.productType = productTypeService.get(id);
//			productType.setDepartment(null);
		} else {
			this.productType = new ProductType();
		}
	}

	@Override
	public ProductType getModel() {
		return productType;
	}

}
