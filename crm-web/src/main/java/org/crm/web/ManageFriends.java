package org.crm.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.registry.infomodel.User;


import com.google.gson.Gson;
import org.crm.dao.SqlLibrary;
import org.crm.service.DealService;
import org.po.Friends;
import org.po.ResultInfo;
import org.po.Web_User;

@WebServlet("/friends")
public class ManageFriends extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if("queryFriends".equals(method)) {
			queryFriends(request,response);
		}else if("deleteFriends".equals(method)) {
			deleteFriends(request,response);
		}else if("queryNick".equals(method)){
			queryNick(request,response);
		}else if("addFriends".equals(method)){
			addFriends(request,response);
		}
	}

	//添加好友
	private void addFriends(HttpServletRequest request, HttpServletResponse response) {
		String nick = request.getParameter("nick");
		String groupId = request.getParameter("groupId");
		Web_User user = getUserFromSession(request);
		Object[] params = {nick,Integer.valueOf(groupId),user.getId()};
		ResultInfo<Friends> friendsAddInfo = DealService.doUpdate(SqlLibrary.SQL_INSERT_FRIEND, params);
		Gson gson = new Gson();
		String result = gson.toJson(friendsAddInfo);
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//查询昵称是否存在
	private void queryNick(HttpServletRequest request, HttpServletResponse response) {
		String nick = request.getParameter("nick");
		Object[] params = {nick};
		ResultInfo<User> userInfo = DealService.doQuery(SqlLibrary.SQL_SHOW_USER,params,User.class);
		Gson gson = new Gson();
		String result = gson.toJson(userInfo);
		System.out.println(result);
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 删除好友
	 * @param request
	 * @param response
	 */
	private void deleteFriends(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Web_User user = getUserFromSession(request);
		Object[] params = {Integer.valueOf(id),user.getId()};
		ResultInfo<Friends> friendsDeleteInfo = DealService.doUpdate(SqlLibrary.SQL_DELETE_FRIEND, params);
		Gson gson = new Gson();
		String result = gson.toJson(friendsDeleteInfo);
		try {
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查询好友
	 * @param request
	 * @param response
	 */
	private void queryFriends(HttpServletRequest request, HttpServletResponse response) {
		Web_User user = getUserFromSession(request);
		Object[] params = {user.getId()};
		ResultInfo<Friends> friendsQueryInfo = DealService.doQuery(SqlLibrary.SQL_SHOW_FRIEND, params, Friends.class);
		request.getSession().setAttribute("friendsQueryInfo",friendsQueryInfo);
		try {
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//获取user对象
	private Web_User getUserFromSession(HttpServletRequest request) {
		return (Web_User)request.getSession().getAttribute("user");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
