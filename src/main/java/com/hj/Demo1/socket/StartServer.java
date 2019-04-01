package com.hj.Demo1.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author: hj
 * Date: 2019-04-01 18:24
 * Description: <启动服务器>
 */
public class StartServer {

    public static void main(String[] args) throws IOException {
        // 创建服务器Socket, 绑定指定端口
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = null;
        System.out.println("*****服务器即将启动，等待客户端连接*****");
        //服务器循环监听客户端的连接请求
        while (true) {
            // 开始监听，等待客户端连接
            socket=serverSocket.accept();
            //多线程通信
            ServerThread thread=new ServerThread(socket);
            thread.start();
        }
    }
}
