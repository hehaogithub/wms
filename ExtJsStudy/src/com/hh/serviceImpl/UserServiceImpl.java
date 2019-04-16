package com.hh.serviceImpl;



import java.util.List;

import com.hh.actionform.UserForm;
import com.hh.dao.UserDao;
import com.hh.domain.PageBean;
import com.hh.domain.Repertory;
import com.hh.domain.User;
import com.hh.service.UserService;


public class UserServiceImpl extends BaseServiceImpl<User> implements UserService  {
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
		this.dao = userDao;
	}
    @Override
	public User login(UserForm user) {
		User existUser = userDao.queryByAccountAndPassword(user);
		return existUser;
	}
	@Override
	public PageBean<User> query(User user, int begin, int pageSize, int repertoryid) {
		PageBean<User> pageBean = new PageBean<User>();
		// 封装当前页数
		
		// 封装每页显示的记录数
		pageBean.setPageSize(pageSize);
		// 封装总记录数
		long totalCount = userDao.getTotalCount(user, repertoryid);
		pageBean.setTotalCount(totalCount);
		// 封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc / pageSize);
		pageBean.setTotalPage(num.intValue());
		// 封装每页显示的数据
		List<User> list = userDao.query( user, begin,pageSize, repertoryid);
		pageBean.setList(list);
		return pageBean;
	}
	
	
	

}
