package com.onexy.pss.service;

import java.util.List;

import com.onexy.pss.domain.SystemDictionaryDetail;

public interface ISystemDictionaryDetailService extends IBaseService<SystemDictionaryDetail> {
	boolean findByName(String name);

	List<SystemDictionaryDetail> getBrands();

	List<SystemDictionaryDetail> getUnits();
}
