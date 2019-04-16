package com.hh.serviceImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.hh.dao.Dao;
import com.hh.domain.PageBean;
import com.hh.service.BaseService;

public class BaseServiceImpl<E> implements BaseService<E> {
	
	protected Dao<E> dao;
	@Override
	public void insert(E entity) {
		dao.insert(entity);
		
	}

	@Override
	public void delete(E entity) {
		dao.delete(entity);
		
	}

	@Override
	public List<E> queryAll() {
		
		return dao.queryAll();
	}

	@Override
	public E queryById(int id) {
		
		return dao.queryById(id);
	}

	@Override
	public void update(E entity) {
	    dao.update(entity);
		
	}

	@Override
	public SQLQuery query(String sql) {
	
		return null;
	}

	@Override
	public List<E> queryByKey(E entity) {
	
		return null;
	}

	@Override
	public PageBean<E> findByPage(int begin,int pageSize) {
		PageBean<E> pageBean = new PageBean<E>();
		// 封装每页显示的记录数
		pageBean.setPageSize(pageSize);
		// 封装总记录数
		long totalCount = dao.findCount();
		pageBean.setTotalCount(totalCount);
		// 封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 封装每页显示的数据
		List<E> list = dao.findByPage(begin, pageSize);
		pageBean.setList(list);
		return pageBean;
		
		
	}

	
	
}
