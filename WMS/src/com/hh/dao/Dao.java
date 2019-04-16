package com.hh.dao;

import java.util.List;

import org.hibernate.SQLQuery;

public interface Dao<E> {
	 long findCount();//查询记录数目
	 void insert(E entity);//插入记录
	 void delete(E entity);//删除记录
	 List<E> queryAll();
	 E queryById(int id);//根据id查询
	 void update(E entity);
	
	 List<E> findByPage(int begin, int pageSize);//分页查找
	 

}
