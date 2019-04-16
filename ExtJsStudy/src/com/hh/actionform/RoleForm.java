package com.hh.actionform;

import org.apache.struts.action.ActionForm;

public class RoleForm extends ActionForm {
	
	private int role_id;// 角色编号
	private String role_name;// 角色名称
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	

}
