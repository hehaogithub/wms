package com.hh.serviceImpl;



import com.hh.dao.ResourceDao;
import com.hh.domain.Resource;
import com.hh.service.ResourceService;

public class ResourceServiceImpl extends BaseServiceImpl<Resource> implements ResourceService  {
	private ResourceDao resourceDao;
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
		this.dao = resourceDao;
	}
}
