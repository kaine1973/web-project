package org.util;

import javax.smartcardio.CommandAPDU;
import java.sql.*;


public class DBUtil {
    private static Connection conn;
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web?useUnicode=true&characterEncoding=utf8", "root", "admin");
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("驱动类异常"+e.getMessage()+e.getStackTrace());
            return null;
        } catch (SQLException e) {
            System.out.println("数据库连接异常"+e.getMessage()+e.getStackTrace());
            return null;
        }
    }
    public static void  close(ResultSet rs, PreparedStatement ps, Connection conn){
        try {
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(ps!=null){
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
