package com.hj.Demo1.util;

import java.io.Serializable;

/**
 * Author: hj
 * Date: 2019-04-01 17:53
 * Description: <客户端和服务器之间传输的指令数据>
 */
public class CommandTransfer implements Serializable {

    private String cmd;//当前操作的命令
    private Object data;//发送的数据
    private boolean flag;//操作是否成功
    private String result;//返回的结果

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
