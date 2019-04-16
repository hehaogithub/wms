package com.hh.domain;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class User extends  ActionForm {
	private int user_id;//用户编号
	private String user_name;//用户姓名
	private String user_sex;//用户性别
	
	private String user_loginaccount;//用户登录账户
	private String user_password;//用户登录密码
	private long user_phone;//用户电话
	private Repertory repertory;//用户所属仓库
	private Role role;//用户所属角色
	public Repertory getRepertory() {
		return repertory;
	}
	public void setRepertory(Repertory repertory) {
		this.repertory = repertory;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_loginaccount() {
		return user_loginaccount;
	}
	public void setUser_loginaccount(String user_loginaccount) {
		this.user_loginaccount = user_loginaccount;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public long getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(long user_phone) {
		this.user_phone = user_phone;
	}
	
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
}
