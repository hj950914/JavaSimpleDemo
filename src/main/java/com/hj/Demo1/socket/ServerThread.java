package com.hj.Demo1.socket;

import com.hj.Demo1.entity.File;
import com.hj.Demo1.entity.User;
import com.hj.Demo1.service.FileService;
import com.hj.Demo1.service.UserService;
import com.hj.Demo1.util.CommandTransfer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Author: hj
 * Date: 2019-04-01 18:30
 * Description: <服务器端线程处理类>
 */
public class ServerThread extends Thread {

    Socket socket = null;
    //对象输入流
    private ObjectInputStream ois = null;
    //对象输出流
    private ObjectOutputStream oos = null;
    //用户业务对象
    private UserService userService = new UserService();
    //文件业务对象
    private FileService fileService = new FileService();

    //初始化socket
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //将字节输入流转换成对象输入流
            ois = new ObjectInputStream(socket.getInputStream());
            //将字节输出流转换成对象输出流
            oos = new ObjectOutputStream(socket.getOutputStream());
            //读取指令
            CommandTransfer transfer = (CommandTransfer) ois.readObject();
            //从客户端接收的指令
            System.out.println(transfer.toString());
            //执行客户端发送到服务器的指令操作
            execute(transfer);
            //将指令数据写到对象输出流
            oos.writeObject(transfer);

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * 执行客户端发送到服务器的指令操作
     * */
    private void execute(CommandTransfer transfer) throws SQLException, ClassNotFoundException {
        //获取当前操作的指令
        String cmd = transfer.getCmd();
        //获取到的操作指令
        System.out.println(cmd);
        //登录
        if (cmd.equals("login")) {
            //获取数据,存入用户实体
            User user = (User) transfer.getData();
            //获取到的用户信息
            System.out.println(user.toString());
            //用户数据库验证
            boolean flag = userService.login(user);
            //数据库验证结果
            System.out.println(flag);
            //设置标记
            transfer.setFlag(flag);
            if (flag) {
                transfer.setResult("登录成功!");
                //标记结果
                System.out.println(transfer.getResult());
            } else {
                transfer.setResult("用户名或密码不正确,请重新登录!");
            }
        } else if (cmd.equals("register")) {//注册
            //获取数据,存入用户实体
            User user = (User) transfer.getData();
            //获取到的用户信息
            System.out.println(user.toString());
            //用户注册
            userService.register(user);
            //验证用户是否成功注册
            boolean flag = userService.login(user);
            //设置标记
            transfer.setFlag(flag);
            if (flag) {
                transfer.setResult("注册成功!请登录");
            } else {
                transfer.setResult("注册失败");
            }
        }else if(cmd.equals("uploadFile")){//文件上传
            //获取文件上传信息,存入文件实体
            File file=(File) transfer.getData();
            //调用文件对象,存入数据库
            fileService.FileSave(file);
            transfer.setResult("上传成功");
        }

    }
}
