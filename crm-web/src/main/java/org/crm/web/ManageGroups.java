package org.crm.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.crm.dao.SqlLibrary;
import org.crm.service.DealService;
import org.po.Groups;
import org.po.ResultInfo;
import org.po.Web_User;
import org.util.StringUtil;


@WebServlet("/groupMgr.do")
public class ManageGroups extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(StringUtil.isNullorEmpty(method) || "query".equals(method)) {
			queryGroups(request,response);
		}else if("insert".equals(method)) {
			insertGroups(request,response);
		}else if("delete".equals(method)) {
			deleteGroups(request,response);
		}else if("revise".equals(method)) {
			reviseGroups(request,response);
		}
	} 
	/**
	 * 修改
	 * @param request
	 * @param response
	 */
	private void reviseGroups(HttpServletRequest request, HttpServletResponse response) {
		String groupId = request.getParameter("groupId");
		String groupName = request.getParameter("groupName");
		Web_User user = getUserFromSession(request);
		Object[] params = {groupName,groupId,user.getId()};
		ResultInfo<Groups> groupReviseInfo = DealService.doUpdate(SqlLibrary.SQL_REVISE_GROUP, params);
	}

	/**
	 * 删除
	 * @param request
	 * @param response
	 */
	private void deleteGroups(HttpServletRequest request, HttpServletResponse response) {
		String groupName = request.getParameter("groupName");
		Web_User user = getUserFromSession(request);
		Object[] params = {groupName,user.getId()};
		ResultInfo<Groups> groupDeleteInfo = DealService.doUpdate(SqlLibrary.SQL_DELETE_GROUP, params);
	}

	/**
	 * 添加
	 * @param request
	 * @param response
	 */
	private void insertGroups(HttpServletRequest request, HttpServletResponse response) {
		String groupName = request.getParameter("groupName");
		Web_User user = getUserFromSession(request);
		Object[] params = {groupName,user.getId()};
		ResultInfo<Groups> groupInsertInfo = DealService.doUpdate(SqlLibrary.SQL_INSERT_GROUP, params);
		
	}

	/**
	 * 查询
	 * @param request
	 * @param response
	 */
	private void queryGroups(HttpServletRequest request, HttpServletResponse response){
		//获取用户对象
		Web_User user = getUserFromSession(request);
		Object[] params = {user.getId()};
		ResultInfo<Groups> groupQueryInfo = DealService.doQuery(SqlLibrary.SQL_SHOW_GROUP,params, Groups.class);
		request.getSession().setAttribute("groupQueryInfo",groupQueryInfo);
		try {
			request.getRequestDispatcher("/chat.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * user对象
	 * @param request
	 * @return
	 */
	private Web_User getUserFromSession(HttpServletRequest request) {
		return (Web_User)request.getSession().getAttribute("user");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
