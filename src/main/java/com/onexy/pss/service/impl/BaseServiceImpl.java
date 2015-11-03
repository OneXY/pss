package com.onexy.pss.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.onexy.pss.dao.BaseDao;
import com.onexy.pss.page.BaseQuery;
import com.onexy.pss.page.PageResult;
import com.onexy.pss.service.IBaseService;

public abstract class BaseServiceImpl<T> implements IBaseService<T> {

	protected BaseDao<T> baseDao;

	private Class<T> entityClass;
	
	public BaseServiceImpl() {
		Type type = getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			entityClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
		}
		logger.info("反射获取的entityClass:"+entityClass);
	}
	
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public void save(T t) {
		baseDao.save(t);
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
	}

	@Override
	public void delete(Serializable id) {
		baseDao.delete(entityClass, id);
	}

	@Override
	public T get(Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public List<T> getAll() {
		return baseDao.getAll(entityClass);
	}
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public PageResult<T> findPageResult(BaseQuery basQuery) {
		return baseDao.findPageResult(basQuery);
	}

	@Override
	public List<String[]> imp(File file) throws Exception {
		List<String[]> list = new ArrayList<String[]>();
		Workbook workbook = Workbook.getWorkbook(file);
		Sheet sheet = workbook.getSheet(0);
		for (int i = 1; i < sheet.getRows(); i++) {
			String[] strs = new String[sheet.getColumns()];
			for (int j = 0; j < strs.length; j++) {
				Cell cell = sheet.getCell(j,i);
				strs[j] = cell.getContents();
			}
			list.add(strs);
		}
		workbook.close();
		return list;
	}

	@Override
	public InputStream download(String[] heads, List<String[]> list)
			throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		WritableWorkbook workbook = Workbook.createWorkbook(os);
		WritableSheet sheet = workbook.createSheet("Sheet1", 0);
		for (int i = 0; i < heads.length; i++) {
			Label label = new Label(i, 0, heads[i]);
			sheet.addCell(label);
		}
		for (int i = 0; i < list.size(); i++) {
			String[] strings = list.get(i);
			for (int j = 0; j < strings.length; j++) {
				Label label = new Label(j, i+1, strings[j]);
				sheet.addCell(label);
			}
		}
		workbook.write();
		workbook.close();
		return  new ByteArrayInputStream(os.toByteArray());
	}

}
