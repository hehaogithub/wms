package com.hh.actionform;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class AddScoreForm extends ActionForm {
	private int sid;
	private String sname;
	private String ssex;
	private String scomputer;
	private String senglish;
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSsex() {
		return ssex;
	}
	public void setSsex(String ssex) {
		this.ssex = ssex;
	}
	public String getScomputer() {
		return scomputer;
	}
	public void setScomputer(String scomputer) {
		this.scomputer = scomputer;
	}
	public String getSenglish() {
		return senglish;
	}
	public void setSenglish(String senglish) {
		this.senglish = senglish;
	}

}
