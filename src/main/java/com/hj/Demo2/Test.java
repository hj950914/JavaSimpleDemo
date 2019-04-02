package com.hj.Demo2;

import com.hj.Demo2.entity.User;
import com.hj.Demo2.service.UserService;
import com.hj.Demo2.util.C3p0Util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author: hj
 * Date: 2019-04-02 16:11
 * Description: <测试c3p0,数据库还是使用和Demo1一样的>
 */
public class Test {

    //用户表对象
    private UserService userService=null;

    @org.junit.jupiter.api.Test
    void test() throws SQLException, ClassNotFoundException {
        Connection connection = C3p0Util.getConnection();
        if (connection !=null){
            System.out.println("c3p0连接数据库成功:"+connection.hashCode());
        }else {
            System.out.println("c3p0连接数据库失败");
        }

        /*
        *  操作数据库测试
        * */
        //用户实体
        User user = new User("小杰姐", "13647920281hj");
        //注册
        userService=new UserService();
        userService.register(user);//成功

    }
}
