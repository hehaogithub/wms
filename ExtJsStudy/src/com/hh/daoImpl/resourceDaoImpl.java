package com.hh.daoImpl;

import java.util.List;

import com.hh.dao.ResourceDao;
import com.hh.domain.Resource;
import com.hh.domain.Role;
public class resourceDaoImpl extends DaoImpl<Resource> implements ResourceDao  {
	public resourceDaoImpl() {
		super(Resource.class);
		
	}

	@Override
	public List<Resource> getMenuList(Role role) {
	
		return null;
	}

	
	
	

	


}
