package com.onexy.pss.web;

import java.io.PrintWriter;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.onexy.pss.domain.Depot;
import com.onexy.pss.page.DepotQuery;
import com.onexy.pss.page.PageResult;
import com.onexy.pss.service.IDepotService;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public class DepotAction extends CRUDAction<Depot> {
	private String name;

	private Depot depot;

	private DepotQuery baseQuery = new DepotQuery();

	private PageResult<Depot> pageResult;

	private IDepotService depotService;


	public void setName(String name) {
		this.name = name;
	}

	public PageResult<Depot> getPageResult() {
		return pageResult;
	}

	public DepotQuery getBaseQuery() {
		return baseQuery;
	}

	public void setBaseQuery(DepotQuery baseQuery) {
		logger.info("正在注入:baseQuery...");
		this.baseQuery = baseQuery;
	}

	public void setDepotService(IDepotService depotService) {
		logger.info("正在注入:depotService...");
		this.depotService = depotService;
	}

	@Override
	public String input() throws Exception {
//		putContext("depts", departmentService.getAll());
		return INPUT;
	}

	public void validateSave() {
		if (StringUtils.isBlank(depot.getName())) {
			addFieldError("name", "用户名必须填写");
		}
	}

	@Override
	@InputConfig(methodName = "input")
	public String save() throws Exception {
		try {
//			Department department = depot.getDepartment();
//			if (department != null && department.getId() == -1) {
//				depot.setDepartment(null);
//			}
			if (id != null) {
				depotService.update(depot);
				addActionMessage("修改成功");
			} else {
				depotService.save(depot);
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
				depotService.delete(id);
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

	@InputConfig(methodName = "input")
	public void checkName() throws Exception {
		logger.info("验证用户名为:" + name + "的用户是否存在");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		if (id != null && depotService.get(id).getName().equals(name)) {
			out.print(true);
		} else {
			out.print(depotService.findByName(name));
		}
	}

	@Override
	protected String list() throws Exception {
		pageResult = depotService.findPageResult(baseQuery);
//		putContext("depts", departmentService.getAll());
		return SUCCESS;
	}

	@Override
	protected void beforeInput() throws Exception {
		if (id != null) {
			this.depot = depotService.get(id);
		}
	}

	@Override
	protected void beforeSave() throws Exception {
		if (id != null) {
			this.depot = depotService.get(id);
//			depot.setDepartment(null);
		} else {
			this.depot = new Depot();
		}
	}

	@Override
	public Depot getModel() {
		return depot;
	}

}
