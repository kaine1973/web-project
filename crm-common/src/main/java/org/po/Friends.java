package org.po;

/**
 * 好友
 * @author 章建华
 *
 */
public class Friends {
	//id
	private Integer id;
	//uid
	private Integer uid;
	//昵称
	private String nick;
	//组id
	private Integer gid;
	public Friends() {
	}
	public Friends(Integer id, Integer uid, String nick, Integer gid) {
		this.id = id;
		this.uid = uid;
		this.nick = nick;
		this.gid = gid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	
}
