package com.hj.Demo1.service;

import com.hj.Demo1.entity.File;
import com.hj.Demo1.util.JdbcConn;

import java.sql.*;

/**
 * Author: hj
 * Date: 2019-04-01 18:03
 * Description: <文件表>
 */
public class FileService {
    private Connection connection = null;

    private PreparedStatement ps = null;

    private ResultSet rs = null;

    //上传文件
    public void FileSave(File file) throws SQLException {
        //获取数据库连接
        connection = JdbcConn.getConnection();
        String sql = "insert into file (fname,fcontent) values(?,?)";
        //预编译
        ps = connection.prepareStatement(sql);
        //设置值
        ps.setString(1, file.getFname());
        ps.setBytes(2, file.getFcontent());
        //执行
        ps.executeUpdate();
        //关闭
        JdbcConn.clostAll(rs,ps,connection);
    }
}
