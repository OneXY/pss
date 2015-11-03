package com.onexy.pss.service;

import com.onexy.pss.domain.Role;

public interface IRoleService extends IBaseService<Role> {
	boolean findByName(String name);
}
