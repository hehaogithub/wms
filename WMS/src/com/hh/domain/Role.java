package com.hh.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts.action.ActionForm;

public class Role extends   ActionForm {
	private int role_id;// 角色编号
	private String role_name;// 角色名称
	private Set<User> users;// 角色包含的用户
	private Set<Resource> resources=new HashSet<Resource>();//角色包含的权限

	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

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
