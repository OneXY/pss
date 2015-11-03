package com.onexy.pss.web;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.onexy.pss.domain.Product;
import com.onexy.pss.domain.ProductType;
import com.onexy.pss.page.PageResult;
import com.onexy.pss.page.ProductQuery;
import com.onexy.pss.service.IProductService;
import com.onexy.pss.service.IProductTypeService;
import com.onexy.pss.service.ISystemDictionaryDetailService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class ProductAction extends CRUDAction<Product> {
	private String name;

	private Product product;
	
	private File upload; 
	
	private String uploadFileName;

	private ProductQuery baseQuery = new ProductQuery();

	private PageResult<Product> pageResult;

	private IProductService productService;

	private IProductTypeService productTypeService;
	
	private ISystemDictionaryDetailService systemDictionaryDetailService;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
	
	public ProductAction() {
		logger.info("正在创建:productAction...");
	}

	public void setName(String name) {
		this.name = name;
	}

	public PageResult<Product> getPageResult() {
		return pageResult;
	}

	public ProductQuery getBaseQuery() {
		return baseQuery;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public void setBaseQuery(ProductQuery baseQuery) {
		logger.info("正在注入:baseQuery...");
		this.baseQuery = baseQuery;
	}

	public void setProductService(IProductService productService) {
		logger.info("正在注入:productService...");
		this.productService = productService;
	}
	
	public void setProductTypeService(IProductTypeService productTypeService) {
		logger.info("正在注入:productTypeService...");
		this.productTypeService = productTypeService;
	}

	public void setSystemDictionaryDetailService(
			ISystemDictionaryDetailService systemDictionaryDetailService) {
		logger.info("正在注入:systemDictionaryDetailService...");
		this.systemDictionaryDetailService = systemDictionaryDetailService;
	}
	
	@Override
	public String input() throws Exception {
		putContext("allParents", productTypeService.getParents());
		
		ArrayList<ProductType> allChildrens = new ArrayList<ProductType>();
		
		ProductType types = product.getTypes();
		
		System.err.println(product);
		System.err.println(types);
		
		if (types != null) {
			ProductType productTypes = types.getParent();
			if (productTypes != null && productTypes.getId() != -1) {
				List<ProductType> childrenList = productTypeService
						.findChildren(productTypes.getId());
				allChildrens.addAll(childrenList);
			}
		} else {
			ProductType productType = new ProductType();
			productType.setId(-1L);
			productType.setName("--请选择--");
			allChildrens.add(productType);
		}
		putContext("allChildrens", allChildrens);
		
		putContext("allBrands", systemDictionaryDetailService.getBrands());
		putContext("allUnits", systemDictionaryDetailService.getUnits());
		
		return INPUT;
	}

	public void validateSave() {
		if (StringUtils.isBlank(product.getName())) {
			addFieldError("name", "用户名必须填写");
		}
	}

	@Override
	@InputConfig(methodName = "input")
	public String save() throws Exception {
		if (upload != null) {
			String webapp = ServletActionContext.getServletContext().getRealPath("/");
			if (id != null) {
				productService.deleteImage(webapp,product);
			}
			Date date = new Date();
			String extension = FilenameUtils.getExtension(uploadFileName);
			String fileName = "upload/" + sdf.format(date) + "." + extension;
			String smallFileName = "upload/" + sdf.format(date) + "_small." + extension;
			File destFile = new File(webapp, fileName);
			File smallDestFile = new File(webapp, smallFileName);
			File parentFile = destFile.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
			FileUtils.copyFile(upload, destFile);
//			Thumbnails.of(upload).scale(0.1F).toFile(smallDestFile);
			Thumbnails.of(upload).size(160, 160).toFile(smallDestFile);
			product.setPic(fileName);
			product.setSmallPic(smallFileName);
		}
		try {
			if (id != null) {
				productService.update(product);
				addActionMessage("修改成功");
			} else {
				productService.save(product);
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
		// HttpServletResponse response = ServletActionContext.getResponse();
		// response.setContentType("text/json;charset=utf-8");
		// PrintWriter out = response.getWriter();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("success", false);
		if (id != null) {
			try {
				product = productService.get(id);
				productService.delete(id);
				productService.deleteImage(ServletActionContext.getServletContext()
						.getRealPath("/"), product);
				// out.print("{\"success\":true,\"msg\":\"删除成功\"}");
				map.put("success", true);
				map.put("msg", "删除成功");
			} catch (Exception e) {
				// out.print("{\"success\":false,\"msg\":\"" + e.getMessage()
				// + "\"}");
				map.put("msg", "异常:" + e.getMessage());
			}
		} else {
			// out.print("{\"success\":false,\"msg\":\"没有删除的"+id+"\"}");
			map.put("msg", "没有删除的"+id);
		}
		putContext("map", map);
		// try {
		// if (id != null) {
		// productService.delete(id);
		// addActionMessage("删除成功");
		// } else {
		// addActionMessage("非法操作");
		// }
		// } catch (Exception e) {
		// addActionError("业务逻辑异常:" + e.getMessage());
		// return execute();
		// }
		return JSON_TYPE;
	}

	@InputConfig(methodName = "input")
	public void checkName() throws Exception {
		logger.info("验证用户名为:" + name + "的用户是否存在");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		if (id != null && productService.get(id).getName().equals(name)) {
			out.print(true);
		} else {
			out.print(productService.findByName(name));
		}
		out.close();
	}

	@Override
	protected String list() throws Exception {
		pageResult = productService.findPageResult(baseQuery);
//		putContext("depts", departmentService.getAll());
		return SUCCESS;
	}
	
	public String bill() throws Exception {
		pageResult = productService.findPageResult(baseQuery);
		return "bill";
	}

	@Override
	protected void beforeInput() throws Exception {
		if (id != null) {
			this.product = productService.get(id);
		} else {
			this.product = new Product();
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		if (id != null) {
			this.product = productService.get(id);
			product.setBrand(null);
			product.setTypes(null);
			product.setUnit(null);
		} else {
			this.product = new Product();
		}
	}
	
	public String findChildren() {
		List<ProductType> childrenList = productTypeService.findChildren(id);
		putContext("map", childrenList);
		return JSON_TYPE;
	}

	@Override
	public Product getModel() {
		return product;
	}

}
