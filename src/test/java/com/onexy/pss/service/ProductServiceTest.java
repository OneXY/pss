package com.onexy.pss.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onexy.pss.page.ProductQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ProductServiceTest {
	@Autowired
	IProductService productService;

	@Test
	public void query() {
		ProductQuery query = new ProductQuery();
		// query.setName("XX");
		// query.setEmail("@qq.com");
		System.out.println(productService.getAll().size());
	}

}
