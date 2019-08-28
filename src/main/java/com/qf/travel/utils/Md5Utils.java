package com.qf.travel.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

public class Md5Utils {
    public static String getpawd(String password){
        String type = "MD5";
        int i = 1024;
        String salt = "abc";
        SimpleHash simpleHash = new SimpleHash(type,password,salt,i);
        return simpleHash.toHex();
    }
}
