package com.onexy.pss.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onexy.pss.domain.Depot;
import com.onexy.pss.domain.Employee;
import com.onexy.pss.domain.Product;
import com.onexy.pss.domain.StockincomeBill;
import com.onexy.pss.domain.StockincomeBillItem;
import com.onexy.pss.domain.Supplier;
import com.onexy.pss.page.StockincomeBillQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StockincomeBillServiceTest {
	@Autowired
	IStockincomeBillService stockincomeBillService;

	@Test
	public void query() {
		StockincomeBillQuery query = new StockincomeBillQuery();
		// query.setName("XX");
		// query.setEmail("@qq.com");
		System.out.println(stockincomeBillService.getAll().size());
	}
	
	@Test
	public void save() throws Exception {
		StockincomeBill bill = new StockincomeBill();
		bill.setDepot(new Depot(1L));
		bill.setInputUser(new Employee(1L));
		bill.setKeeper(new Employee(1L));
		bill.setSupplier(new Supplier(1L));
		bill.setVdate(new Date());

		List<StockincomeBillItem> items = new ArrayList<StockincomeBillItem>();
		StockincomeBillItem item1 = new StockincomeBillItem();
		item1.setDescs("备注1");
		item1.setNum(new BigDecimal(1));
		item1.setPrice(new BigDecimal(1));
		item1.setProduct(new Product(1L));
		items.add(item1);

		StockincomeBillItem item2 = new StockincomeBillItem();
		item2.setDescs("备注2");
		item2.setNum(new BigDecimal(2));
		item2.setPrice(new BigDecimal(2));
		item2.setProduct(new Product(2L));
		items.add(item2);

		// 总金额，总数量
		BigDecimal totalAmount = new BigDecimal(0);
		BigDecimal totalNum = new BigDecimal(0);

		for (StockincomeBillItem billItem : items) {
			// 建立多方到一方的关系
			billItem.setBill(bill);
			// 计算小计
			billItem.setAmount(billItem.getPrice().multiply(billItem.getNum()));
			// 累加总金额，总数量
			totalAmount = totalAmount.add(billItem.getAmount());
			totalNum = totalNum.add(billItem.getNum());
		}
		// 总金额，总数量
		bill.setTotalAmount(totalAmount);
		bill.setTotalNum(totalNum);
		// 已经建立一方到多方的关系
		bill.setItems(items);

		stockincomeBillService.save(bill);
	}

}
