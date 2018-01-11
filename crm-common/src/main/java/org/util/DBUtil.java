package org.util;

import javax.smartcardio.CommandAPDU;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
    private static Connection conn;
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "admin");
            System.out.println("数据库连接成功");
            return conn;
        } catch (ClassNotFoundException e) {
            System.out.println("驱动类异常"+e.getMessage()+e.getStackTrace());
            return null;
        } catch (SQLException e) {
            System.out.println("数据库连接异常"+e.getMessage()+e.getStackTrace());
            return null;
        }
    }
}
