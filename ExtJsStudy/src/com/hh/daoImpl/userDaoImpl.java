package com.hh.daoImpl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.hh.actionform.UserForm;
import com.hh.dao.UserDao;
import com.hh.domain.Repertory;
import com.hh.domain.User;

public class userDaoImpl extends DaoImpl<User> implements UserDao{
	
public userDaoImpl() {
		super(User.class);
		// TODO Auto-generated constructor stub
	}

@Override
public User queryByAccountAndPassword(UserForm user) {
	String hql = "from User where user_loginaccount = ? and user_password = ?";
	List<User> list = this.getHibernateTemplate().find(hql, user.getUser_loginaccount(), user.getUser_password());
	if (list.size() > 0) {
		return list.get(0);
	} else {
		return null;
	}
}






@Override
public List<User> query(User user,int begin,int pageSize,int id) {
	String sql="select * from ("+getsqlWhere(user,id)+") us where rm>"+begin+"and rm<="+(begin+pageSize);
	List<User> list= getSession().createSQLQuery(sql).addEntity(User.class).list();
	return list;
	
}

@Override
public String getsqlWhere(User user, int repertoryid) {
	String sql="select rownum rm,u.* from users u where u.repertory_id="+repertoryid;
	if(user.getUser_name()!=null){
		sql=sql+" and u.user_name like "+"'%"+user.getUser_name()+"%'";
	}
    return sql;
 }

@Override
public int getTotalCount(User user,int repertoryid) {
	String sql=getsqlWhere(user,repertoryid);
	List<User> list= getSession().createSQLQuery(sql).addEntity(User.class).list();
	return list.size();
}



}
