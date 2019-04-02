package com.hj.Demo1.util;

import java.sql.*;

/**
 * Author: hj
 * Date: 2019-04-01 13:48
 * Description: <JDBC连接>
 */
public class JdbcConn {

    //useSSL=false安全连接指定false
    private static final String URL = "jdbc:mysql://localhost:3306/Demo1?useSSL=false";
    private static final String username = "root";
    private static final String password = "13647920281hj";

    //数据库连接对象
    private static Connection connection = null;

    static {
        //1.加载驱动程序
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //返回数据库连接
    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            //2.获得数据库的连接
            connection = DriverManager.getConnection(URL, username, password);
        } else {
            return connection;
        }
        return connection;
    }

    //关闭
    public static void clostAll(ResultSet rs, PreparedStatement stmt, Connection conn) {
        try {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void clostAll(PreparedStatement stmt, Connection conn) {
        try {
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
