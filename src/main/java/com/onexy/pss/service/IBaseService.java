package com.onexy.pss.service;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import com.onexy.pss.page.BaseQuery;
import com.onexy.pss.page.PageResult;

public interface IBaseService<T> {
	void save(T t);

	void update(T t);

	void delete(Serializable id);

	T get(Serializable id);

	List<T> getAll();
	
	PageResult<T> findPageResult(BaseQuery basQuery);
	
	List<String[]> imp(File file) throws Exception;
	
	InputStream download(String[] heads, List<String[]> list) throws Exception;
	
}
