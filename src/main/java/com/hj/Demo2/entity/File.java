package com.hj.Demo2.entity;

import java.io.Serializable;

/**
 * Author: hj
 * Date: 2019-04-01 17:43
 * Description: <文件实体类>
 */
public class File implements Serializable {

    private int fid;//文件id
    private String fname;//文件名
    private byte[] fcontent;//文件字节流

    public File() {
    }

    public File(String fname, byte[] fcontent) {
        this.fname = fname;
        this.fcontent = fcontent;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public byte[] getFcontent() {
        return fcontent;
    }

    public void setFcontent(byte[] fcontent) {
        this.fcontent = fcontent;
    }

    public File(int fid, String fname, byte[] fcontent) {
        this.fid = fid;
        this.fname = fname;
        this.fcontent = fcontent;
    }
}
