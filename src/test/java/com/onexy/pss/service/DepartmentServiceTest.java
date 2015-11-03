package com.onexy.pss.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onexy.pss.page.DepartmentQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartmentServiceTest {
	@Autowired
	IDepartmentService departmentService;

	@Test
	public void query() {
		DepartmentQuery query = new DepartmentQuery();
		// query.setName("XX");
		// query.setEmail("@qq.com");
		System.out.println(departmentService.getAll().size());
	}

}
