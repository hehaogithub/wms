package com.hh.actionform;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm {
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
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
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
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRepertory_name() {
		return repertory_name;
	}
	public void setRepertory_name(String repertory_name) {
		this.repertory_name = repertory_name;
	}
	public int getRepertory_id() {
		return repertory_id;
	}
	public void setRepertory_id(int repertory_id) {
		this.repertory_id = repertory_id;
	}
	private int user_id;//用户编号
	private String user_name;//用户姓名
	private String user_sex;//用户性别
	
	private String user_loginaccount;//用户登录账户
	private String user_password;//用户登录密码
	private long user_phone;//用户电话
	private String role_name;//
	private int role_id;
	private String repertory_name;
	private int repertory_id;

}
