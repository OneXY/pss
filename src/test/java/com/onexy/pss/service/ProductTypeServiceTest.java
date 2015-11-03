package com.onexy.pss.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onexy.pss.page.ProductTypeQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ProductTypeServiceTest {
	@Autowired
	IProductTypeService productTypeService;

	@Test
	public void query() {
		ProductTypeQuery query = new ProductTypeQuery();
		// query.setName("XX");
		// query.setEmail("@qq.com");
		System.out.println(productTypeService.getAll().size());
	}

}
