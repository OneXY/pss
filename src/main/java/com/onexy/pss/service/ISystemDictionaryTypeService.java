package com.onexy.pss.service;

import com.onexy.pss.domain.SystemDictionaryType;

public interface ISystemDictionaryTypeService extends IBaseService<SystemDictionaryType> {
	boolean findByName(String name);
}
