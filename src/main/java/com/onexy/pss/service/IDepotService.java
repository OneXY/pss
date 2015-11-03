package com.onexy.pss.service;

import com.onexy.pss.domain.Depot;

public interface IDepotService extends IBaseService<Depot> {
	boolean findByName(String name);
}
