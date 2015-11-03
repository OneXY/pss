package com.onexy.pss.service;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onexy.pss.domain.PurchaseBillItem;
import com.onexy.pss.page.PurchaseBillItemQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PurchaseBillItemServiceTest {
	@Autowired
	IPurchaseBillItemService purchaseBillItemService;

	@Test
	public void query() {
		PurchaseBillItemQuery query = new PurchaseBillItemQuery();
		// query.setName("XX");
		// query.setEmail("@qq.com");
		System.out.println(purchaseBillItemService.getAll().size());
	}
	
	@Test
	public void testFindByGrop() throws Exception {
		PurchaseBillItemQuery baseQuery = new PurchaseBillItemQuery();
		String groupBy = "o.bill.supplier.name";
		baseQuery.setGroupBy(groupBy);
		baseQuery.setStatus(0);
		List<Object[]> list = purchaseBillItemService.findByGroup(baseQuery);
		for (Object[] objects : list) {
			System.err.println(Arrays.toString(objects));
			List<PurchaseBillItem> items = purchaseBillItemService.findItems(baseQuery, objects[0]);
			for (PurchaseBillItem purchaseBillItem : items) {
				System.err.println(purchaseBillItem);
			}
		}
	}

}
