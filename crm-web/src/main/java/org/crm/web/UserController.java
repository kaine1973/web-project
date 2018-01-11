package org.crm.web;

import org.crm.service.UserService;
import org.po.Tb_user;
import org.po.ResultInfo;
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
                    cookies[i].setMaxAge(0);
                System.out.println("删除cookie");
            }
            response.sendRedirect("login.jsp");
            return;
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response) {
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        ResultInfo<List<Tb_user>> ri = new ResultInfo();
        if (StringUtil.isNullorEmpty(uname) || StringUtil.isNullorEmpty(pwd)) {
            ri = new ResultInfo(0, "用户名或密码不能为空", null);
            try {
                request.getRequestDispatcher("register.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(ri.getMsg());
            return;
        }
        request.setAttribute("name",uname);
        pwd = StringUtil.encypt(pwd);
        List<Object> list = new ArrayList<Object>();
        String sql = "SELECT ID,NAME,PWD,AGE,NICK,HEAD,LABEL FROM TB_USER WHERE NAME = ?";
        list.add(uname);
        ResultInfo<List<Tb_user>> res = UserService.query(sql, list.toArray());

            if (res.getStatus() == 0) {
                list.add(pwd);
                list.add(uname);
                String sqlu = "INSERT INTO TB_USER(NAME,PWD,NICK) VALUES (?,?,?)";
                ResultInfo<List<Tb_user>> result = UserService.update(sqlu, list.toArray());
                if (result.getStatus() == 1) {
                    try {
                        System.out.println("注册成功"+result.getMsg());
                        response.sendRedirect("login.jsp");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("注册失败"+result.getMsg());
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
                System.out.println(res.getMsg()+res.getMsg());
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
        System.out.println(remember+"rem");
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
        String sql = "SELECT ID,NAME,PWD,AGE,NICK,HEAD,LABEL FROM TB_USER WHERE NAME = ? AND PWD = ?";
        List list = new ArrayList();
        list.add(uname);
        list.add(pwd);
        ResultInfo<List<Tb_user>> res = UserService.query(sql, list.toArray());
        if (res.getStatus() > 0) {
            try {
                List<Tb_user> t = res.getT();
                Tb_user user = t.iterator().next();
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
            System.out.println("登录失败"+res.getMsg());
            request.setAttribute("result", res);
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
