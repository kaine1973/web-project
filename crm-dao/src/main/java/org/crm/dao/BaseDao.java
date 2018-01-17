package org.crm.dao;

import org.util.DBUtil;
import org.util.StringUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDao {
	
	/**
	 * 查询记录
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 */
	public static List getQueryRows(String sql, Object[] params, Class cls) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			if(null != params) {
				for(int i=0; i<params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd= rs.getMetaData();
			int count = rsmd.getColumnCount();
			while(rs.next()) {
				Object obj =  cls.newInstance();

				for(int i=0; i<count; i++) {
					//获取字段名
					String Columnname = rsmd.getColumnName(i+1);
					//获取类属性
					Field field = cls.getDeclaredField(Columnname);
					//获取set方法
					Method method = cls.getDeclaredMethod("set"+ StringUtil.firstChar2Upper(Columnname),field.getType());
					method.invoke(obj, rs.getObject(Columnname));
				}
				list.add(obj);
			}
		} catch (InstantiationException e) {
			System.out.println("BaseDao报错"+e.getMessage()+e.getStackTrace());
		} catch (InvocationTargetException e) {
			System.out.println("BaseDao报错"+e.getMessage()+e.getStackTrace());
		} catch (NoSuchMethodException e) {
			System.out.println("BaseDao报错"+e.getMessage()+e.getStackTrace());
		} catch (SQLException e) {
			System.out.println("BaseDao报错"+e.getMessage()+e.getStackTrace());
		} catch (IllegalAccessException e) {
			System.out.println("BaseDao报错"+e.getMessage()+e.getStackTrace());
		} catch (NoSuchFieldException e) {
			System.out.println("BaseDao报错"+e.getMessage()+e.getStackTrace());
		} finally {
			DBUtil.close(rs, ps, conn);
		} 
		return list;
	}
	
	/**
	 * 查询单条记录
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 */
	public static Object getQueryOneRow(String sql, Object[] params, Class cls) {
		List list = getQueryRows(sql,params,cls);
		if(null == list)
			return null;
		return list.get(0);
	}
	
	/**
	 * 更新
	 * @param sql
	 * @param params
	 * @return
	 */
	public static boolean update(String sql, Object[] params) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			if(null != params) {
				for(int i=0; i<params.length; i++) {
					ps.setObject(i+1, params[i]);
				}
			}
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			result = -1;
		}
		if(0 < result)
			return true;
		return false;
	}
}
