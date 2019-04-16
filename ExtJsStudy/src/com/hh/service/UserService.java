package com.hh.service;

import java.util.List;

import com.hh.actionform.UserForm;
import com.hh.domain.PageBean;
import com.hh.domain.User;

public interface UserService extends BaseService<User> {
	User login(UserForm user);
	PageBean<User> query(User user,int begin,int pageSize,int repertoryid);//根据仓库id查询用户信息
	

}
