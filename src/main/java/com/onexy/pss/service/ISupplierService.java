package com.onexy.pss.service;

import com.onexy.pss.domain.Supplier;

public interface ISupplierService extends IBaseService<Supplier> {
	boolean findByName(String name);
}
