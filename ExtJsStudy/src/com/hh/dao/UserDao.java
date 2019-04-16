package com.hh.dao;

import java.util.List;

import com.hh.actionform.UserForm;
import com.hh.domain.Repertory;
import com.hh.domain.User;

public interface UserDao extends Dao<User> {
	User queryByAccountAndPassword(UserForm user);//根据姓名和密码查找用户，用于登录或添加用户时的判断
	String getsqlWhere(User user,int repertoryid);//查询条件封装方法
	int getTotalCount(User user,int repertoryid);//查询总记录数
	 List<User> query(User user,int begin,int pageSize,int id);//原生sql查询
       


	

}
