package com.hj.Demo2.service;

import com.hj.Demo1.util.JdbcConn;
import com.hj.Demo2.entity.User;
import com.hj.Demo2.util.C3p0Util;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author: hj
 * Date: 2019-04-01 17:58
 * Description: <用户表>
 */
public class UserService {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    //用户注册
    public void register(User user) throws SQLException {
        //获取数据库连接对象
        connection = C3p0Util.getConnection();
        //获取操作数据库的对象
        ps = connection.prepareStatement("insert into user (username,password) values(?,?)");
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getPassword());
        ps.executeUpdate();
    }

    //用户登录
    public boolean login(User user) throws ClassNotFoundException {
        //查询语句,注意where后面的拼接问题
        String sql = "select username,password from user where username='" + user.getUsername() + "' and password='" + user.getPassword() + "' ";
        try {

            connection = C3p0Util.getConnection();
            ps = connection.prepareStatement(sql);
            ps.execute();
            rs = ps.executeQuery();
            //如果找到用户,返回true
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
