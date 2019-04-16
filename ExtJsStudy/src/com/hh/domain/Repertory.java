package com.hh.domain;

import java.util.Set;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class Repertory extends ActionForm{
	private int repertory_id;//�ֿ���
	private String repertory_name;//�ֿ���
	private String remark;//备注
	private Set<User> users;//仓库包含的用户
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public int getRepertory_id() {
		return repertory_id;
	}
	public void setRepertory_id(int repertory_id) {
		this.repertory_id = repertory_id;
	}
	public String getRepertory_name() {
		return repertory_name;
	}
	public void setRepertory_name(String repertory_name) {
		this.repertory_name = repertory_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
