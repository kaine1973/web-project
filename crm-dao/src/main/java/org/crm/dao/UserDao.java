package org.crm.dao;

import org.po.Web_User;
import org.springframework.stereotype.Repository;
import org.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {


    public static List<Web_User> query(String sql, Object[] objects) throws SQLException {
        Connection conn = DBUtil.getConnection();
        Web_User user = new Web_User();
        List<Web_User> list = new ArrayList();
        PreparedStatement ps = null;
        ps = conn.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            ps.setObject(i + 1, objects[i]);
        }
        ResultSet result = ps.executeQuery();
        while (result.next()) {

            list.add(new Web_User(result.getInt("id"),
                    result.getString("uname"),
                    result.getString("pwd"),
                    result.getByte("age"),
                    result.getString("nick"),
                    result.getString("head"),
                    result.getString("mood")));
        }
        ps.close();
        conn.close();
        return list;
    }


    public static int update(String sql, Object[] objects) throws SQLException {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < objects.length; i++) {
            ps.setObject(i + 1, objects[i]);
        }
        int i = ps.executeUpdate();
        ps.close();
        conn.close();
        return i;
    }
}
