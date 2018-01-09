package org.crm.dao;

public class User {
	private String uname = "admin";
	private String upwd = "admin";
	public boolean Query(String uname,String upwd) {
		if(this.uname.equals(uname)&&this.upwd.equals(upwd)) {
			return true;
		}
		return false;
	}
}
