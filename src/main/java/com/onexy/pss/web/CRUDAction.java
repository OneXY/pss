package com.onexy.pss.web;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

public abstract class CRUDAction<T> extends BaseAction implements
		ModelDriven<T>, Preparable {
	protected Long id;

	public void setId(Long id) {
		System.out.println("Long id:"+id);
		this.id = id;
	}

	@Override
	public void prepare() throws Exception {
	}

	@Override
	@InputConfig(methodName="execute")
	public final String execute() throws Exception {
		return list();
	}

	public final void prepareInput() throws Exception {
		beforeInput();
	}

	public final void prepareSave() throws Exception {
		beforeSave();
	}

	/**
	 * 列表
	 * 
	 * @return String:列表视图
	 * @throws Exception
	 */
	protected abstract String list() throws Exception;

	/**
	 * 保存
	 * 
	 * @return String:保存视图
	 * @throws Exception
	 */
	public abstract String save() throws Exception;

	/**
	 * 删除
	 * 
	 * @return String:删除视图
	 * @throws Exception
	 */
	public abstract String delete() throws Exception;

	/**
	 * input的前置方法
	 * 
	 * @throws Exception
	 */
	protected abstract void beforeInput() throws Exception;

	/**
	 * save的前置方法
	 * 
	 * @throws Exception
	 */
	protected abstract void beforeSave() throws Exception;

}