package com.onexy.pss.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onexy.pss.page.DepotQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepotServiceTest {
	@Autowired
	IDepotService depotService;

	@Test
	public void query() {
		DepotQuery query = new DepotQuery();
		// query.setName("XX");
		// query.setEmail("@qq.com");
		System.out.println(depotService.getAll().size());
	}

}
