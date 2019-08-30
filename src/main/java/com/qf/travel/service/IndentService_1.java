package com.qf.travel.service;

import com.qf.travel.pojo.Indent;

import java.util.List;
import java.util.Map;

public interface IndentService_1 {
    List<Indent> queryAll(int page,int rows);
    int getTotalCount(String uname,int rows);
    List<Indent> findIndent(int page,int rows,String uname);
    boolean IndentManUp(String istate,int id);
    int getAllmaxpage(int rows);
    List<Indent> loadAll(int page,int rows,String istate);
    Indent getUnameById(int id);

    int getCountStyIsta(String istate,String stype);
    List<Indent> loadAll11();
}
