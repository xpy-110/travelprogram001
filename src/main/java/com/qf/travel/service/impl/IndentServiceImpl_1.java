package com.qf.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.qf.travel.mapper.IndentMapper_1;
import com.qf.travel.pojo.Indent;
import com.qf.travel.service.IndentService;
import com.qf.travel.service.IndentService_1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndentServiceImpl_1 implements IndentService_1 {
    @Autowired
    private IndentMapper_1 indentMapper_1;
    @Override
    public List<Indent> queryAll(int page, int rows) {
        PageHelper.startPage(page, rows);
        return indentMapper_1.queryAll();
    }

    @Override
    public int getTotalCount(String uname,int rows) {
        int c = indentMapper_1.getTotalCount(uname);
        int maxpage = c%rows==0?c/rows:c/rows+1;
        return maxpage;
    }

    @Override
    public List<Indent> findIndent(int page, int rows, String uname) {
        PageHelper.startPage(page, rows);
        return indentMapper_1.findIndent(uname);
    }

    @Override
    public boolean IndentManUp(String istate,int id) {
        Map<String,Object> map = new HashMap<>();
        map.put("istate",istate);
        map.put("id",id);
        int c = indentMapper_1.IndentManUp(map);
        return c>0?true:false;
    }

    @Override
    public int getAllmaxpage(int rows) {
        int c = indentMapper_1.getAllCount();
        int maxpage = c%rows==0?c/rows:c/rows+1;
        return maxpage;
    }

    @Override
    public List<Indent> loadAll(int page,int rows,String istate) {
        PageHelper.startPage(page, rows);
        return indentMapper_1.loadAll(istate);
    }

    @Override
    public Indent getUnameById(int id) {
        return indentMapper_1.getUnameById(id);
    }
}
