package org.crm.service;

public class UserService {
	public boolean login(String uname,String upwd) {
		return new org.crm.dao.User().Query(uname, upwd)?true:false;
	}
}
