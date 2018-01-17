package org.crm.dao;

public class SqlLibrary {
	public static final String SQL_SHOW_GROUP = "SELECT id,groups,uid FROM tb_groups WHERE uid = ?";
	public static final String SQL_INSERT_GROUP = "INSERT INTO tb_groups (groups,uid) VALUES (?,?)";
	public static final String SQL_DELETE_GROUP = "DELETE FROM tb_groups WHERE groups = ? AND uid = ?";
	public static final String SQL_REVISE_GROUP = "UPDATE tb_groups SET  groups = ? WHERE id = ? AND uid = ?";
	public static final String SQL_SHOW_FRIEND = "SELECT id,uid,nick,gid FROM tb_friends WHERE uid = ?";
	public static final String SQL_DELETE_FRIEND = "DELETE FROM tb_friends WHERE id = ? AND uid = ?";
	public static final String SQL_INSERT_FRIEND = "INSERT INTO tb_friends (nick,gid,uid) VALUES (?,?,?)";
	public static final String SQL_SHOW_USER = "SELECT id,uname,pwd,head,mood,age,nick FROM web_user WHERE nick = ?";
}
