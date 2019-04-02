package com.hj.Demo2.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author: hj
 * Date: 2019-04-02 15:59
 * Description: <c3p0>
 */
public class C3p0Util {

    //获取c3p0数据源;
    private static ComboPooledDataSource dataSource =null;
    private static Connection connection=null;

    /*
    *
    * 从数据源中获取一个连接对象,就是获取一个Connection对象
    */
    public static Connection getConnection(){
        if (connection==null){
            try {
                dataSource=new ComboPooledDataSource();
                connection=dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            return connection;
        }
        return connection;
    }


}
