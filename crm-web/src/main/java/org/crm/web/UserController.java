package org.crm.web;

import org.crm.service.UserService;
import org.po.ResultInfo;
import org.po.Web_User;
import org.util.StringUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userMgr.do")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        System.out.println(act);
        if ("login".equals(act)) {
            login(request, response);
            return;
        }else if("register".equals(act)){
            register(request,response);
            return;
        }else if("logout".equals(act)){
            Cookie[] cookies = request.getCookies();
            for(int i = 0;i<cookies.length;i++){
                    Cookie c = cookies[i];
                    c.setMaxAge(0);
                    response.addCookie(c);
            }
            response.sendRedirect("login.jsp");
            return;
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response) {
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        ResultInfo<List<Web_User>> ri = new ResultInfo();
        if (StringUtil.isNullorEmpty(uname) || StringUtil.isNullorEmpty(pwd)) {
            ri = new ResultInfo(0, "用户名或密码不能为空", null);
            try {
                request.getRequestDispatcher("register.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        request.setAttribute("name",uname);
        pwd = StringUtil.encypt(pwd);
        List<Object> list = new ArrayList<Object>();
        String sql = "SELECT ID,UNAME,PWD,AGE,NICK,HEAD,MOOD FROM WEB_USER WHERE UNAME = ?";
        list.add(uname);
        ResultInfo<List<Web_User>> res = UserService.query(sql, list.toArray());

            if (res.getStatus() == 0) {
                list.add(pwd);
                list.add(uname);
                String sqlu = "INSERT INTO WEB_USER(UNAME,PWD,NICK) VALUES (?,?,?)";
                ResultInfo<List<Web_User>> result = UserService.update(sqlu, list.toArray());
                if (result.getStatus() == 1) {
                    try {
                        response.sendRedirect("login.jsp");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    request.setAttribute("result", res);
                    try {
                        request.getRequestDispatcher("register.jsp").forward(request,response);
                    } catch (ServletException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            } else {
                request.setAttribute("result", res);
                try {
                    request.getRequestDispatcher("register.jsp").forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }

    }

    public void login(HttpServletRequest request, HttpServletResponse response) {
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        String remember = request.getParameter("remember");
        if (StringUtil.isNullorEmpty(uname) || StringUtil.isNullorEmpty(pwd)) {
            new ResultInfo(0, "用户名或密码不能为空", null);
            try {
                request.getRequestDispatcher("login.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        request.setAttribute("name",uname);
        pwd = StringUtil.encypt(pwd);
        String sql = "SELECT ID,UNAME,PWD,AGE,NICK,HEAD,MOOD FROM WEB_USER WHERE UNAME = ? AND PWD = ?";
        List list = new ArrayList();
        list.add(uname);
        list.add(pwd);
        ResultInfo<List<Web_User>> res = UserService.query(sql, list.toArray());
        if (res.getStatus() > 0) {
            try {
                List<Web_User> t = res.getT();
                Web_User user = t.iterator().next();
                request.getSession().setAttribute("user", user);
                if("on".equals(remember)){
                    Cookie c = new Cookie("user",uname+"_"+pwd);
                    c.setMaxAge(3*24*60*60);
                    response.addCookie(c);
                }
                response.sendRedirect("main.jsp");
                request.removeAttribute("name");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            request.getSession().setAttribute("result",res);
            try {
                request.getRequestDispatcher("login.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
