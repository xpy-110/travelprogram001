package com.qf.travel.service;

import com.qf.travel.pojo.Indent;

import java.util.List;
import java.util.Map;

public interface IndentService {
    /**
     * 查出所有订单信息
     * @return
     */
    public List<Indent> findIndent(String uname,int page, int rows);

    /**
     * 查询总的订单数
     * @param rows
     * @return
     */
    public int calcMaxPage(String uname,int rows);
    /**
     * 模糊查询所有信息
     * */
    List<Indent> queryAll(String uuu);
    /*
     * 模糊查询某个用户的某个信息
     * */
    List<Indent> queryAllByUname(String uname,String uuu);
}
