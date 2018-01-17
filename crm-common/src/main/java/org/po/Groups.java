package org.po;

public class Groups {
	//id
	private Integer id;
	//组
	private String groups;
	//uid
	private Integer uid;
	public Groups() {
	}
	public Groups(Integer id, String groups, Integer uid) {
		this.id = id;
		this.groups = groups;
		this.uid = uid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
}
