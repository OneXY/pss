package com.onexy.pss.service;

import com.onexy.pss.domain.Resource;

public interface IResourceService extends IBaseService<Resource> {
	boolean findByName(String name);
}
