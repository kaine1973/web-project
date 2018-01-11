package org.crm.web.filter;

import org.crm.dao.User;
import org.crm.service.UserService;
import org.crm.web.UserController;
import org.po.ResultInfo;
import org.po.Tb_user;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = {"/login.jsp"},filterName = "/a")
public class AutoLogin implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        String user = null;
        String auto = null;
        Cookie[] ck = request.getCookies();
        if(ck==null){
            chain.doFilter(req,resp);
            return;
        }
        for(int i = 0;i<ck.length;i++){
            user = ck[i].getName();
            if(user.equals("user")){
                auto = ck[i].getValue();
                break;
            }

        }
        if(!"user".equals(user)){
            chain.doFilter(req,resp);
            return;
        }
        String name = auto.split("_")[0];
        String pwd = auto.split("_")[1];
        String sql = "SELECT ID,NAME,PWD,AGE,NICK,HEAD,LABEL FROM TB_USER WHERE NAME = ? AND PWD = ?";

        ResultInfo<List<Tb_user>> res = UserService.query(sql, new Object[]{name, pwd});
        System.out.println(res.getT());
        if(res.getT()!=null){
            List<Tb_user> t = res.getT();
            Tb_user tu = t.iterator().next();
            System.out.println(tu.getName());
            request.getSession().setAttribute("user", tu);
            request.setAttribute("result",res);
            response.sendRedirect("main.jsp");
            return;
        }


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
