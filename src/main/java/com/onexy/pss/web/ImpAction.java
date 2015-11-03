package com.onexy.pss.web;

import java.io.File;
import java.util.List;

import com.onexy.pss.domain.Department;
import com.onexy.pss.domain.Employee;
import com.onexy.pss.service.IDepartmentService;
import com.onexy.pss.service.IEmployeeService;

public class ImpAction extends BaseAction {
	private IEmployeeService employeeService;
	private IDepartmentService departmentService;
	private File upload;
	private Integer type;
	private Integer count = 0;
	
	public Integer getCount() {
		return count;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String upload() throws Exception {
		if (upload == null) {
			return NONE;
		}
		switch (type) {
		case 1:
			List<String[]> list = employeeService.imp(upload);
			for (String[] strs : list) {
				Employee employee = new Employee();
				employee.setName(strs[1]);
				employee.setPassword(strs[2]);
				employee.setEmail(strs[3]);
				employee.setAge(Integer.parseInt(strs[4]));
				String deptName = strs[5];
				employee.setDepartment(departmentService
						.findDeptByName(deptName));
				employeeService.save(employee);
				count++;
			}
			break;
		case 2:
			List<String[]> list2 = departmentService.imp(upload);
			for (String[] strs : list2) {
				Department department = new Department();
				department.setName(strs[1]);
				departmentService.save(department);
				count++;
			}
			break;
		default:
			break;
		}
		return SUCCESS;
	}
}
