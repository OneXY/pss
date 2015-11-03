package com.onexy.pss.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseQuery {
	private int pageSize = 10;
	private int currentPage = 1;
	private StringBuilder countHql;
	private StringBuilder hql;
	private StringBuilder whereHql;
	private List paramList;

	public BaseQuery(Class entityClass) {
		countHql = new StringBuilder("SELECT COUNT(o) FROM "
				+ entityClass.getName() + " o");
		hql = new StringBuilder("SELECT o FROM " + entityClass.getName() + " o");
		whereHql = new StringBuilder();
		paramList = new ArrayList();
	}

	protected abstract void addWhere();

	protected final void addWhere(String where, Object... objects) {
		if (paramList.size() == 0) {
			countHql.append(" WHERE ").append(where);
			hql.append(" WHERE ").append(where);
			whereHql.append(" WHERE ").append(where);
		} else {
			countHql.append(" AND ").append(where);
			hql.append(" AND ").append(where);
			whereHql.append(" AND ").append(where);
		}
		paramList.addAll(Arrays.asList(objects));
	};

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	private boolean flag;

	private void builderWhere() {
		if (!flag) {
			flag = true;
			addWhere();
		}
	}

	public String getCountHql() {
		builderWhere();
		return countHql.toString();
	}

	public String getHql() {
		builderWhere();
		return hql.toString();
	}

	public List getParamList() {
		builderWhere();
		return paramList;
	}
	
	public String getWhereHql() {
		builderWhere();
		return whereHql.toString();
	}
}
