package org.crm.web;

import org.crm.service.ArticleService;
import org.po.ResultInfo;
import org.po.Web_Article;
import org.po.Web_User;
import org.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.crm.service.ArticleService.listArticle;


@WebServlet("/articleMgr.do")
public class ArticleController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        if("new".equals(act)){
            newArticle(request,response);
        }else if("list".equals(act)){
            list(request,response);
        }
    }


    private void list(HttpServletRequest request, HttpServletResponse response) {
        Web_User user = (Web_User)request.getSession().getAttribute("user");

        String sql = "select id, title, content, create_date, update_date, uid from Web_Article where uid = ?";

        List<Object> list = new ArrayList<Object>();
        list.add(user.getId());
        ResultInfo<List<Web_Article>> ri = listArticle(sql, list.toArray());
        try {
            request.getSession().setAttribute("articleResult",ri);
            response.getWriter().write(ri.getStatus());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void newArticle(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        if(StringUtil.isNullorEmpty(title)||StringUtil.isNullorEmpty(content)){
            return;
        }
        String sql = "INSERT INTO Web_ARTICLE(TITLE,CONTENT,create_date,update_date,UID) VALUES(?,?,SYSDATE(),SYSDATE(),?)";
        Web_User user = (Web_User)request.getSession().getAttribute("user");
        List<Object> list = new ArrayList<Object>();
        list.add(title);
        list.add(content);
        list.add(user.getId());
        ResultInfo<List<Web_Article>> ri = ArticleService.update(sql, list.toArray());
        try {
            request.getSession().setAttribute("articleResult",ri);
            response.getWriter().write("/article/article.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
