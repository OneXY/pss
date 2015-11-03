package com.onexy.pss.service.impl;
import java.util.Date;
import java.util.List;

import com.onexy.pss.domain.Employee;
import com.onexy.pss.domain.PurchaseBill;
import com.onexy.pss.service.IPurchaseBillService;


public class PurchaseBillServiceImpl extends BaseServiceImpl<PurchaseBill> implements IPurchaseBillService {
	@Override
	public boolean findByName(String name) {
		String hql = "select count(o) from PurchaseBill o where o.name = ?";
		List<Long> result = baseDao.findByHql(hql, name);
		if (result.get(0).intValue()>0) {
			logger.info("用户名已存在");
			return false;
		}
		logger.info("用户名不存在");
		return true;
	}

	@Override
	public String auditor(Long bullId, Employee auditor) {
		//#{-2:'--请选择--',0:'待审',1:'已审',-1:'作废'}
		PurchaseBill bill = get(bullId);//持久状态
		if (bill.getStatus() == 1) {
			return "此单已经审核";
		} else if (bill.getStatus() == -1) {
			return "此单已经作废";
		}
		bill.setAuditor(auditor);
		bill.setAuditorTime(new Date());
		bill.setStatus(1);
		return "此单成功审核";
	}
}
