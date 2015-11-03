package com.onexy.pss.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onexy.pss.page.ResourceQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ResourceServiceTest {
	@Autowired
	IResourceService resourceService;

	@Test
	public void query() {
		ResourceQuery query = new ResourceQuery();
		// query.setName("XX");
		// query.setEmail("@qq.com");
		System.out.println(resourceService.getAll().size());
	}

}
