package com.hh.serviceImpl;



import com.hh.dao.RoleDao;
import com.hh.domain.Role;
import com.hh.service.RoleService;

public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService  {
	private RoleDao roleDao;
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
		this.dao = roleDao;
	}
	

	

}
