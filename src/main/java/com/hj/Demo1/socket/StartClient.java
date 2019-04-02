package com.hj.Demo1.socket;

/**
 * Author: hj
 * Date: 2019-04-01 19:40
 * Description: <客户端1>
 */
public class StartClient {

    public static void main(String[] args){
        SocketClient socketClient=new SocketClient();
        //显示主菜单
        socketClient.showMainMenu();
    }
}
