package org.crm.dao;

import org.po.Web_Article;
import org.springframework.stereotype.Component;
import org.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ArticleDao {
    public static int update(String sql, Object[] obj) throws SQLException {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        for(int i = 0;i<obj.length;i++){
            ps.setObject(i+1,obj[i]);
        }
        int i = ps.executeUpdate();
        ps.close();
        conn.close();
        return i;
    }
    public static List<Web_Article> query(String sql, Object[] obj) throws SQLException {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        List<Web_Article> list = new ArrayList();

        for (int i = 0; i < obj.length; i++) {
            ps.setObject(i + 1, obj[i]);
        }
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Web_Article(rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getDate("create_date"),
                    rs.getDate("update_date"),
                    rs.getInt("uid")));
        }
        ps.close();
        conn.close();
        return list;
    }

}
