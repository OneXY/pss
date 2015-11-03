package com.onexy.pss.service;

import com.onexy.pss.domain.Employee;
import com.onexy.pss.domain.PurchaseBill;

public interface IPurchaseBillService extends IBaseService<PurchaseBill> {
	boolean findByName(String name);

	String auditor(Long bullId, Employee auditor);
}
