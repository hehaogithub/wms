package com.hh.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts.action.ActionForm;

public class Resource extends ActionForm{
	private int id;//资源编号
	private String url;//资源访问路径
	private String text;//资源名称
	private int leaf;//是否为叶子
	private int pid;//父节点编号
	private Set<Role> roles=new HashSet<Role>();//资源对应的角色
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getLeaf() {
		return leaf;
	}
	public void setLeaf(int leaf) {
		this.leaf = leaf;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}

}
