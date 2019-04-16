package com.hh.dao;

import java.util.List;

import com.hh.domain.Repertory;

public interface RepertoryDao extends Dao<Repertory> {
	
	Repertory queryByName(Repertory repertory);//根据姓名精确查询
	List<Repertory> queryByPageAndKey(int begin,int pageSize,Repertory repertory);//根据条件分页模糊查询
	long findCountByKey(Repertory repertory);//根据条件获得结果数

}
