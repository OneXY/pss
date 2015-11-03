package com.onexy.pss.service;

import com.onexy.pss.domain.Department;

public interface IDepartmentService extends IBaseService<Department> {
	boolean findByName(String name);
	Department findDeptByName(String name);
}
