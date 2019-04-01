package com.hj.Demo1.socket;

import com.hj.Demo1.entity.User;
import com.hj.Demo1.util.CommandTransfer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Author: hj
 * Date: 2019-04-01 19:10
 * Description: <客户端>
 */
public class SocketClient {

    Scanner scanner = new Scanner(System.in);

    private Socket socket = null;//客户端Socket

    //主菜单
    public void showMainMenu() {
        System.out.println("*****欢迎使用imooc文件上传器*****");
        System.out.println("1.登录\n2.注册\n3.退出");
        System.out.println("*****************************");
        System.out.println("请选择：");
        //获取用户的选择
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                showLogin();//登录
                break;
            case 2:
                showRegister();//注册
                break;
            case 3:
                System.out.println("再见，感谢您对本系统的支持！");
            default:
                System.out.println("输入有误！");
                //终止虚拟机
                System.exit(0);
        }
    }


    //用户注册
    private void showRegister() {
    }

    //用户登录
    private void showLogin() {
        User user=new User();
        CommandTransfer transfer=new CommandTransfer();
        int count=0;//登录次数
        while (true){
            count++;
            if (count>3){
                System.out.println("您已连续三次登录失败，程序退出！");
                System.exit(0);
            }
            System.out.println("请输入用户名:");
            user.setUsername(scanner.next());
            System.out.println("请输入密码:");
            user.setPassword(scanner.next());
            transfer.setCmd("login");
            transfer.setData(user);

            try {
                //创建客户端Socket,指定服务器地址和端口
                socket=new Socket("127.0.0.1", 8888);
                //将数据发送到服务器
                sendData(transfer);
                System.out.println("*************************");
                //获取服务器返回的数据
                transfer=getData();
                System.out.println("结果:"+transfer.getResult());
                System.out.println("*************************");
                //如果登录成功,则不再重复执行登录
                if(transfer.isFlag()){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                closeAll();
            }

        }
    }

    //资源关闭
    private void closeAll() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取响应
    private CommandTransfer getData() {
        ObjectInputStream ois=null;
        CommandTransfer transfer=null;
        try {
            ois=new ObjectInputStream(socket.getInputStream());
            transfer=(CommandTransfer) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return transfer;
    }


    //发送请求
    private void sendData(CommandTransfer transfer) {
        ObjectOutputStream oos=null;
        try {
            oos=new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(transfer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
