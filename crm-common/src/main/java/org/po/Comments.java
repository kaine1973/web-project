package org.po;

import java.util.Date;

/**
 * 日志评论
 * @author 章建华
 *
 */
public class Comments {
	//编号
	private Integer id;
	//评论内容
	private String comments;
	//评论时间
	private Date time;
	//评论者id
	private Integer uid;
	//评论者昵称
	private String nick;
	//日志
	private Integer aid;
	public Comments() {
	}
	public Comments(Integer id, String comments, Date time, Integer uid, String nick, Integer aid) {
		this.id = id;
		this.comments = comments;
		this.time = time;
		this.uid = uid;
		this.nick = nick;
		this.aid = aid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
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
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	
}
