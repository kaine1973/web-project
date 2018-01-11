package org.crm.dao;

import org.po.Tb_user;
import org.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {


    public static List<Tb_user> query(String sql, Object[] objects) {
        Connection conn = DBUtil.getConnection();
        List<Tb_user> list = new ArrayList();
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < objects.length; i++) {
                ps.setObject(i + 1, objects[i]);
            }
            ResultSet result = ps.executeQuery();
            while (result.next()) {

                list.add(new Tb_user(result.getInt("id"),
                        result.getString("name"),
                        result.getString("pwd"),
                        result.getByte("age"),
                        result.getString("nick"),
                        result.getString("head"),
                        result.getString("label")));

            }
            ps.close();
            conn.close();
            return list;
        } catch (SQLException e) {
            System.out.println("查询用户异常"+e.getMessage()+e.getStackTrace());
            try {
                conn.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return null;
        }

    }

    public static int update(String sql, Object[] objects) throws SQLException {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        for(int i = 0; i<objects.length;i++) {
            ps.setObject(i+1,objects[i]);
        }
        int i = ps.executeUpdate();
        ps.close();
        conn.close();


        return i;
    }
}
