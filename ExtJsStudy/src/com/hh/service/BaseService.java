 package com.hh.service;

import java.util.List;

import org.hibernate.SQLQuery;

import com.hh.domain.PageBean;

public interface BaseService<E> {
	 void insert(E entity);//插入记录
	 void delete(E entity);//删除记录
	 List<E> queryAll();
	 E queryById(int id);//根据id查询
	 void update(E entity);
	 SQLQuery query(String sql);
	 List<E> queryByKey(E entity);//条件查询
	 PageBean<E> findByPage(int begin,int pageSize);//分页查找
}