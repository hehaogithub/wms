package com.hh.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hh.dao.Dao;

public class DaoImpl<E> extends HibernateDaoSupport implements Dao<E> {
	protected Class<E> entityClass;

	public DaoImpl(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public long findCount() {
		return (Long) getSession().createQuery("select count(*) from " + entityClass.getName()).uniqueResult();
	}

	@Override
	public void insert(E entity) {
		this.getHibernateTemplate().save(entity);
		
	}

	@Override
	public void delete(E entity) {
		this.getHibernateTemplate().delete(entity);
		
	}

	
	@Override
	public List<E> queryAll() {
	   
		return this.getHibernateTemplate().find("from "+entityClass.getName());
	}

	@Override
	public E queryById(int id) {
		
		 return this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public void update(E entity) {
		this.getHibernateTemplate().update(entity);
		
	}

	
	

	@Override
	public List<E> findByPage(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		List<E> list = this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
		
	}

	
	

	
	

}
