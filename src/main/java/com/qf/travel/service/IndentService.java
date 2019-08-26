package com.qf.travel.service;

import com.qf.travel.pojo.Indent;

import java.util.List;

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
}
