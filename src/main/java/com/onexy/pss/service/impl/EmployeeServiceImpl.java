package com.onexy.pss.service.impl;

import java.util.List;

import com.onexy.pss.domain.Employee;
import com.onexy.pss.service.IEmployeeService;

public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements
		IEmployeeService {

	@Override
	public boolean findByName(String name) {
		String hql = "select count(o) from Employee o where o.name = ?";
		List<Long> result = baseDao.findByHql(hql, name);
		if (result.get(0).intValue() > 0) {
			logger.info("用户名已存在");
			return false;
		}
		logger.info("用户名不存在");
		return true;
	}

	@Override
	public Employee findForLogin(String username, String password) {
		String hql = "select o from Employee o where o.name=? and o.password=?";
		List<Employee> list = baseDao.findByHql(hql, username, password);
		if (list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<String> getAllResourceMethods() {
		return baseDao.findByHql("select o.method from Resource o");
	}

	@Override
	public List<String> findMethodsByLoginUser(Long id) {
		String hql = "select distinct res.method from Employee o join o.roles r join r.resources res where o.id = ?";
		return baseDao.findByHql(hql, id);
	}

	@Override
	public List<Employee> findBuerysByDeptName(String deptName) {
		String hql = "select e from Employee e where e.department.name=?";
		return baseDao.findByHql(hql, deptName);
	}

	@Override
	public List<Employee> findKeepersByDeptName(String deptName) {
		String hql = "select e from Employee e where e.department.name=?";
		return baseDao.findByHql(hql, deptName);
	}

}
