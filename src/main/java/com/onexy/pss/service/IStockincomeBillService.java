package com.onexy.pss.service;

import com.onexy.pss.domain.Employee;
import com.onexy.pss.domain.StockincomeBill;

public interface IStockincomeBillService extends IBaseService<StockincomeBill> {
	boolean findByName(String name);

	String auditor(Long id, Employee loginUser);
}
