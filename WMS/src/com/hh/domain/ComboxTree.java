package com.hh.domain;

import java.util.List;

public class ComboxTree extends Tree {
	private boolean checked;
	private List<ComboxTree> children;
	public ComboxTree(){
		
	}
	public ComboxTree(int id, String text, int pid) {
        this.setId(id);
        this.setPid(pid);
        this.setText(text);
    }
    
	public boolean isChecked() {
		return checked;
	}

	public List<ComboxTree> getChildren() {
		return children;
	}

	public void setChildren(List<ComboxTree> children) {
		this.children = children;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String toString(){
		return null;
		
		 
		 
	
		
	}

}
