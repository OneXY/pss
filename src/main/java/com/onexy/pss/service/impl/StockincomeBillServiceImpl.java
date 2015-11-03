package com.onexy.pss.service.impl;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.onexy.pss.domain.Depot;
import com.onexy.pss.domain.Employee;
import com.onexy.pss.domain.Product;
import com.onexy.pss.domain.ProductStock;
import com.onexy.pss.domain.StockincomeBill;
import com.onexy.pss.domain.StockincomeBillItem;
import com.onexy.pss.service.IDepotService;
import com.onexy.pss.service.IProductStockService;
import com.onexy.pss.service.IStockincomeBillService;


public class StockincomeBillServiceImpl extends BaseServiceImpl<StockincomeBill> implements IStockincomeBillService {
	private IProductStockService productStockService;
	private IDepotService depotService;
	
	public void setProductStockService(IProductStockService productStockService) {
		this.productStockService = productStockService;
	}
	
	public void setDepotService(IDepotService depotService) {
		this.depotService = depotService;
	}
	
	@Override
	public boolean findByName(String name) {
		String hql = "select count(o) from StockincomeBill o where o.name = ?";
		List<Long> result = baseDao.findByHql(hql, name);
		if (result.get(0).intValue()>0) {
			logger.info("用户名已存在");
			return false;
		}
		logger.info("用户名不存在");
		return true;
	}

	@Override
	public String auditor(Long id, Employee auditor) {
		StockincomeBill bill = get(id);
		if (bill == null) {
			throw new RuntimeException("此入库单不存在...");
		}
		if (bill.getStatus() == 1) {
			return "此单已经审核";
		} else if (bill.getStatus() == -1) {
			return "此单已经作废";
		}
		bill.setStatus(1);
		bill.setAuditor(auditor);
		bill.setAuditorTime(new Date());
		// 显示更新一下
		update(bill);
		
		Depot depot = bill.getDepot();
		String hql = "select o from ProductStock o where o.depot=? and o.product=?";
		List<StockincomeBillItem> items = bill.getItems();
		for (StockincomeBillItem billItem : items) {
			Product product = billItem.getProduct();
			List<ProductStock> list = baseDao.findByHql(hql, depot, product);
			if (list.size() == 0) {// 添加
				ProductStock productStock = new ProductStock();
				// 小计
				productStock.setAmount(billItem.getAmount());
				// 仓库
				productStock.setDepot(depot);
				// 入库时间
				productStock.setIncomeDate(new Date());
				// 入库数量
				productStock.setNum(billItem.getNum());
				// 入库价格
				productStock.setPrice(billItem.getPrice());
				// 入库产品
				productStock.setProduct(product);
				// 保存
				productStockService.save(productStock);
			} else if (list.size() == 1) {// 更新
				ProductStock productStock = list.get(0);
				// 产品id 入库时间 入库数量 价格 小计
				// 100 03 100 10 1000
				// 100 05 200 30 6000
				// 加权平均法
				// 100 05 300 (1000+6000)/300 7000
				// 总金额(1000+6000)
				BigDecimal totalAmount = productStock.getAmount().add(billItem.getAmount());
				// 总数量
				BigDecimal totalNum = productStock.getNum().add(billItem.getNum());
				// 小计
				productStock.setAmount(totalAmount);
				// 入库数量
				productStock.setNum(totalNum);
				// 入库价格:保留2位小数点，四舍五入
				BigDecimal price = totalAmount.divide(totalNum, 2, BigDecimal.ROUND_HALF_EVEN);
				productStock.setPrice(price);
				// 入库时间
				productStock.setIncomeDate(new Date());
				// 修改
				productStockService.update(productStock);
			} else {// >1即时库存表：业务要求：同一个仓库里面的产品是唯一的
				throw new RuntimeException("同一个仓库里面的产品不是唯一的");
			}
		}

		// 仓库总数量，总金额(循环的外面写)
		depot.setCapacity(depot.getCapacity().add(bill.getTotalNum()));
		depot.setTotlaAmount(depot.getTotlaAmount().add(bill.getTotalAmount()));
		depotService.update(depot);
		return "此单成功审核";
	}
}
