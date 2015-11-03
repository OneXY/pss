package com.onexy.pss.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onexy.pss.page.EmployeeQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmployeeServiceTest {
	@Autowired
	IEmployeeService employeeService;
	
	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAll() {
		System.out.println(employeeService.getAll());
	}

	@Test
	public void query() {
		EmployeeQuery query = new EmployeeQuery();
//		query.setName("XX");
//		query.setEmail("@qq.com");
		query.setCurrentPage(2);
		System.out.println(employeeService.findPageResult(query));
	}
	
}
