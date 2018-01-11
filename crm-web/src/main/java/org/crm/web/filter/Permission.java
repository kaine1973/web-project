package org.crm.web.filter;

import org.po.Tb_user;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"*.do","*.jsp"},filterName = "/b")
public class Permission implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        Tb_user user = (Tb_user)request.getSession().getAttribute("user");
        String act = request.getParameter("act");
        String requestURI = request.getRequestURI();
        if(requestURI.contains("login.jsp")||requestURI.contains("register.jsp")||"login".equals(act)||"register".equals(act)||"logout".equals(act)){
            chain.doFilter(req, resp);
            return;
        }
        if(user==null){
            response.sendRedirect("login.jsp");
            return;
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
