package com.qf.travel.pojo;

import lombok.Data;

import java.io.Serializable;
public class Permission implements Serializable {
    private int id;//int(11) NOT NULL
    private String pname;//varchar(20) NULL
    private String mname;//varchar(20) NULL
    private int pId;//varchar(20) NULL
    private String check;

    public Permission() {
    }

    public Permission(int id, String pname, String mname, int pId, String check) {
        this.id = id;
        this.pname = pname;
        this.mname = mname;
        this.pId = pId;
        this.check = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", pname='" + pname + '\'' +
                ", mname='" + mname + '\'' +
                ", pId='" + pId + '\'' +
                ", check='" + check + '\'' +
                '}';
    }
}
