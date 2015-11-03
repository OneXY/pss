package com.onexy.pss.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.onexy.pss.page.BaseQuery;
import com.onexy.pss.page.PageResult;

public class BaseDao<T> extends HibernateDaoSupport {
	public void save(T t) {
		getHibernateTemplate().save(t);
	}

	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	public void delete(Class<T> entityClass, Serializable id) {
		T t = get(entityClass, id);
		if (t != null) {
			getHibernateTemplate().delete(t);
		}
	}

	public T get(Class<T> entityClass, Serializable id) {
		return getHibernateTemplate().get(entityClass, id);
	}

	public List<T> getAll(Class<T> entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}

	public PageResult<T> findPageResult(final BaseQuery baseQuery) {
		logger.info("高级查询Hql:"+baseQuery.getHql());
		logger.info("高级查询CountHql:"+baseQuery.getCountHql());
		logger.info("高级查询条件:"+baseQuery.getParamList());
		Long countLong = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<Long>() {
					@Override
					public Long doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(baseQuery
								.getCountHql());
						builder(query, baseQuery);
						return (Long) query.uniqueResult();
					}
				});
		System.out.println("count=" + countLong.intValue());
		if (countLong == 0) {
			return new PageResult<T>();
		}
		final PageResult<T> pageResult = new PageResult<>(countLong.intValue(),
				baseQuery.getPageSize(), baseQuery.getCurrentPage());

		List<T> rows = getHibernateTemplate().executeWithNativeSession(
				new HibernateCallback<List<T>>() {
					@Override
					public List<T> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(baseQuery.getHql());
						builder(query, baseQuery);
						int maxResults = pageResult.getPageSize();
						int firstResult = (pageResult.getCurrentPage() - 1)
								* maxResults;
						query.setFirstResult(firstResult).setMaxResults(
								maxResults);
						return query.list();
					}
				});
		pageResult.setRows(rows);
		return pageResult;
	}

	public List findByHql(String hql, Object... objects) {
		logger.info("BaseDao:"+hql+" 参数:"+Arrays.toString(objects));
		return getHibernateTemplate().find(hql, objects);
	}

	private void builder(Query query, BaseQuery baseQuery) {
		int index = 0;
		for (Object obj : baseQuery.getParamList()) {
			query.setParameter(index++, obj);
		}
	}
}
