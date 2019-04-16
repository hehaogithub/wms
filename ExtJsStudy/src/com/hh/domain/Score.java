package com.hh.domain;

import org.apache.struts.action.ActionForm;

public class Score extends ActionForm {
	private int sid;
	private String sname;
	private String ssex;
	private int scomputer;
	private int senglish;
	
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
	public int getScomputer() {
		return scomputer;
	}
	public void setScomputer(int scomputer) {
		this.scomputer = scomputer;
	}
	public int getSenglish() {
		return senglish;
	}
	public void setSenglish(int senglish) {
		this.senglish = senglish;
	}
	
	@Override
    public String toString() {
        return "sid=" + sid + ",sname=" + sname + ",ssex=" + ssex+",scomputer="+scomputer
        		+",senglish="+senglish;
    }
	

}
