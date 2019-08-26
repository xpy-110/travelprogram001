package com.qf.travel.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class User implements Serializable {
    private int uid;//int(11) NOT NULL
    private String uname;//varchar(20) NULL
    private String upwd;//varchar(20) NULL
    private String email;//varchar(20) NULL
    private String realname;//varchar(20) NULL
    private String tel;//varchar(20) NULL
    private String sex;//varchar(20) NULL
    private String birth;//varchar(20) NULL
    private String createtime;
    public User() {
    }

    public User(int uid, String uname, String upwd, String email, String realname, String tel, String sex, String birth) {
        this.uid = uid;
        this.uname = uname;
        this.upwd = upwd;
        this.email = email;
        this.realname = realname;
        this.tel = tel;
        this.sex = sex;
        this.birth = birth;
    }

    public User(int uid, String uname, String upwd, String email, String realname, String tel, String sex, String birth, String createtime) {
        this.uid = uid;
        this.uname = uname;
        this.upwd = upwd;
        this.email = email;
        this.realname = realname;
        this.tel = tel;
        this.sex = sex;
        this.birth = birth;
        this.createtime = createtime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upwd='" + upwd + '\'' +
                ", email='" + email + '\'' +
                ", realname='" + realname + '\'' +
                ", tel='" + tel + '\'' +
                ", sex='" + sex + '\'' +
                ", birth='" + birth + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }
}
