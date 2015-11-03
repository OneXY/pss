package com.onexy.pss.service;

import java.util.List;

import com.onexy.pss.domain.Employee;

public interface IEmployeeService extends IBaseService<Employee> {
	boolean findByName(String name);

	Employee findForLogin(String username,String password);
	
	List<String> getAllResourceMethods();
	
	List<String> findMethodsByLoginUser(Long id);
	
	List<Employee> findBuerysByDeptName(String deptName);

	List<Employee> findKeepersByDeptName(String deptName);
}
