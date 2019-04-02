package com.hj.Demo1.socket;

import com.hj.Demo1.entity.File;
import com.hj.Demo1.entity.User;
import com.hj.Demo1.util.CommandTransfer;

import java.io.*;
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
        System.out.println("*****欢迎使用简单文件上传器*****");
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
        User user = new User();
        CommandTransfer transfer = new CommandTransfer();
        while (true) {
            System.out.println("请输入注册的用户名:");
            user.setUsername(scanner.next());
            //密码需要输入2遍
            System.out.println("请输入注册的密码:");
            String password1 = scanner.next();
            System.out.println("请再次输入密码:");
            String password2 = scanner.next();
            //如果密码不相等
            if (!password1.equals(password2)) {
                System.out.println("2次密码输入不一致");
                System.out.println("*************************");
                //重复
                continue;
            }
            user.setPassword(password1);
            transfer.setCmd("register");
            transfer.setData(user);

            try {
                //创建客户端Socket,指定服务器地址和端口
                socket = new Socket("127.0.0.1", 8888);
                //数据发送到服务器
                sendData(transfer);
                System.out.println("*************************");
                //获取服务器返回的数据
                transfer = getData();

                System.out.println(" 结果:" + transfer.getResult());
                System.out.println("*************************");
                //如果注册成功,则退出循环
                if (transfer.isFlag()) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeAll();
            }
        }
        // 注册成功后显示登录
        showLogin();
    }

    //用户登录
    private void showLogin() {
        User user = new User();
        CommandTransfer transfer = new CommandTransfer();
        int count = 0;//登录次数
        while (true) {
            count++;
            if (count > 3) {
                System.out.println("您已连续三次登录失败，程序退出！ ");
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
                socket = new Socket("127.0.0.1", 8888);
                //将数据发送到服务器
                sendData(transfer);
                System.out.println("*************************");
                //获取服务器返回的数据
                transfer = getData();
                System.out.println(transfer.getResult());
                System.out.println("*************************");
                //如果登录成功,则不再重复执行登录
                if (transfer.isFlag()) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeAll();
            }

        }
        //登录成功后进行文件的上传
        showUploadFile();
    }

    //文件上传
    public void showUploadFile() {
        while (true) {
            System.out.println("请输入上传文件的绝对路径(如e:/huangjie/dog.jpg)");
            String path = scanner.next();
            //定义文件实体类
            File file = null;
            //定义文件IO
            java.io.File fileIo=new java.io.File(path);
            //判断文件是否存在
            if (!fileIo.isFile()){
                continue;
            }
            //定义字节输入流
            FileInputStream fis = null;
            //定义字节缓冲流
            BufferedInputStream bis = null;
            //获取文件名,这里使用返回子串
            String fname = path.substring(path.lastIndexOf("/") + 1);
            try {
                fis = new FileInputStream(fileIo);
                //读取文件字节数用于定义字节数组大小
                byte[] fcontent = new byte[fis.available()];
                //字节输入流转字节输入缓冲
                bis = new BufferedInputStream(fis);
                //将读取的内容存入字节数组
                bis.read(fcontent);
                //存入文件实体
                file = new File(fname, fcontent);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bis != null) {
                        bis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /*
             * 存入数据
             * */
            CommandTransfer transfer = new CommandTransfer();
            transfer.setCmd("uploadFile");
            transfer.setData(file);

            /*
             * 传送数据到服务器
             * */
            try {
                socket = new Socket("127.0.0.1", 8888);
                //发送数据给服务器
                sendData(transfer);
                //接收服务器返回数据
                transfer = getData();
                System.out.println(transfer.getResult());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeAll();
            }
            //退出循环
            break;
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
        ObjectInputStream ois = null;
        CommandTransfer transfer = null;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            transfer = (CommandTransfer) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return transfer;
    }


    //发送请求
    private void sendData(CommandTransfer transfer) {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(transfer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
