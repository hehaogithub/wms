package com.hh.service;

import java.util.List;

import com.hh.domain.PageBean;
import com.hh.domain.Repertory;

public interface RepertoryService extends BaseService<Repertory> {

	Repertory queryByName(Repertory repertory);//根据姓名精确查询
	PageBean<Repertory> queryByPageAndKey(int begin,int pageSize,Repertory repertory);//根据条件分页模糊查询
	
	

}
