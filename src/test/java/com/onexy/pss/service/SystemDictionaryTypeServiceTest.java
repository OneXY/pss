package com.onexy.pss.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onexy.pss.page.SystemDictionaryTypeQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SystemDictionaryTypeServiceTest {
	@Autowired
	ISystemDictionaryTypeService systemDictionaryTypeService;

	@Test
	public void query() {
		SystemDictionaryTypeQuery query = new SystemDictionaryTypeQuery();
		// query.setName("XX");
		// query.setEmail("@qq.com");
		System.out.println(systemDictionaryTypeService.getAll().size());
	}

}
