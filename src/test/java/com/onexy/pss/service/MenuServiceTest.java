package com.onexy.pss.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onexy.pss.page.MenuQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MenuServiceTest {
	@Autowired
	IMenuService menuService;

	@Test
	public void query() {
		MenuQuery query = new MenuQuery();
		// query.setName("XX");
		// query.setEmail("@qq.com");
		System.out.println(menuService.getAll().size());
		System.err.println(menuService.findParentByLoginUser(1L));
		System.err.println(menuService.findChildrenByLoginUser(1L, 1L));
	}

}
