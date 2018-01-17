package org.crm.service;

import org.crm.dao.ArticleDao;
import org.po.ResultInfo;
import org.po.Web_Article;

import java.sql.SQLException;
import java.util.List;

public class ArticleService {
    public static ResultInfo<List<Web_Article>> update(String sql, Object[] obj){
        ResultInfo ri = new ResultInfo();
        try {
            int update = ArticleDao.update(sql, obj);
            if(update>0){
                ri.setStatus(1);
                ri.setMsg("保存成功");
                ri.setT(null);
            }else{
                ri.setStatus(0);
                ri.setMsg("保存失败，请重试");
                ri.setT(null);
            }
        } catch (SQLException e) {
            ri.setStatus(-1);
            ri.setMsg("保存失败"+e.getMessage());
            ri.setT(null);
        }
        return ri;
    }
    public static ResultInfo<List<Web_Article>> listArticle(String sql, Object[] obj){
        ResultInfo<List<Web_Article>> ri = new ResultInfo();
        try {
            List<Web_Article> result = ArticleDao.query(sql, obj);
            if(result!=null){
                ri.setStatus(1);
                ri.setMsg("查询成功");
                ri.setT(result);
            }else{
                ri.setStatus(0);
                ri.setMsg("查询失败");
                ri.setT(null);
            }
        } catch (SQLException e) {
            ri.setStatus(-1);
            ri.setMsg("系统异常，查询失败");
            ri.setT(null);
            e.printStackTrace();
        }
        return ri;

    }

    public static ResultInfo deleteArticle(String sql, Object[] objects) {
        ResultInfo<List<Web_Article>> ri = new ResultInfo();
        try {
            int res = ArticleDao.delete(sql, objects);
            if(res>0){
               ri.setStatus(1);
                ri.setMsg("删除成功");
            }else{
                ri.setStatus(0);
                ri.setMsg("删除失败");
            }
        } catch (SQLException e) {
            ri.setStatus(0);
            ri.setMsg("删除失败"+e.getMessage());
            System.out.println("删除文章时的数据库异常");
        }
        return ri;
    }
}
