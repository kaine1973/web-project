package org.crm.service;

import org.crm.dao.BaseDao;
import org.po.Groups;
import org.po.ResultInfo;

import java.util.List;

public class DealService {

	/**
	 * 处理得到的数据
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 */
	public static ResultInfo doQuery(String sql, Object[] params, Class cls){
		ResultInfo dealInfo = new ResultInfo();
		List res = BaseDao.getQueryRows(sql, params, cls);
		if(res.isEmpty()) {
			dealInfo.setStatus(0);
			dealInfo.setMsg("fail");
			dealInfo.setT(null);
		}else {
			dealInfo.setStatus(1);
			dealInfo.setMsg("success");
			dealInfo.setT(res);
		}
		return dealInfo;
	}
	
	/**
	 * 处理更新
	 * @param sql
	 * @param params
	 * @return
	 */
	public static ResultInfo doUpdate(String sql, Object[] params) {
		ResultInfo dealInfo = new ResultInfo();
		boolean flag = BaseDao.update(sql, params);
		if(flag) {
			dealInfo.setStatus(1);
			dealInfo.setMsg("success");
			dealInfo.setT(null);
		}else {
			dealInfo.setStatus(0);
			dealInfo.setMsg("fail");
			dealInfo.setT(null);
		}
		return dealInfo;
	}
	
	/**
	 * 处理用户
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 */
	public ResultInfo<Object> doQueryOne(String sql, Object[] params, Class cls){
		ResultInfo<Object> dealInfo = new ResultInfo<>();
		Object obj = BaseDao.getQueryOneRow(sql, params, cls);
		if(null == obj) {
			dealInfo.setStatus(0);
			dealInfo.setMsg("fail");
		}else {
			dealInfo.setStatus(1);
			dealInfo.setMsg("success");
			dealInfo.setT(obj);
		}
		return dealInfo;
	}
}
