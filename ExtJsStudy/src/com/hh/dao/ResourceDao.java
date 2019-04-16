package com.hh.dao;

import java.util.List;

import com.hh.domain.Resource;
import com.hh.domain.Role;

public interface ResourceDao extends Dao<Resource> {
	List<Resource> getMenuList(Role role);

}
