package com.qf.travel.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qf.travel.mapper.IndentMapper;
import com.qf.travel.pojo.Indent;
import com.qf.travel.service.IndentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndentServiceImpl implements IndentService {

    @Resource
    private IndentMapper indentMapper;
    /**
     * 查出所有订单信息
     * @return
     */
    @Override
    public List<Indent> findIndent(String uname,int page,int rows) {
        PageHelper.startPage(page, rows);
        List<Indent> indent = indentMapper.findIndent(uname);
        System.out.println("service"+indent);
        return indent;
    }
    /**
     * 得出总订单数
     * @param rows
     * @return
     */
    @Override
    public int calcMaxPage(String uname,int rows) {
        int count=indentMapper.getTotalCount(uname);
        System.out.println(count);
        return count%rows==0?count/rows:count/rows+1;
    }
    /**
     * 得出总订单数
     * @param rows
     * @return
     */
    @Override
    public int calcMaxPage1(String uname, String istate, int rows) {
        int count=indentMapper.getTotalCount1(uname,istate);
        System.out.println(count);
        return count%rows==0?count/rows:count/rows+1;
    }

    @Override
    public List<Indent> queryAll(String uuu) {
        return indentMapper.queryAll(uuu);
    }

    @Override
    public List<Indent> queryAllByUname(String uname, String uuu) {
        Map<String,String> map = new HashMap<>();
        map.put("uname",uname);
        map.put("uuu",uuu);
        return indentMapper.queryAllByUname(map);
    }

    @Override
    public int deleteById(int id) {
        int i = indentMapper.deleteById(id);
        return i;
    }

    @Override
    public boolean deleteIds(List<Integer> list) {
        int c = indentMapper.deleteIds(list);
        boolean bool= c>0?true:false;
        return bool;
    }
    /**
     * 根据不同状态查出不同订单
     *
     */
    @Override
    public List<Indent> findIndent1(String istate,String uname, int page, int rows) {
        PageHelper.startPage(page, rows);
        Map<String,String> map=new HashMap<>();
        map.put("istate",istate);
        map.put("uname",uname);
        List<Indent> indent = indentMapper.findIndent1(map);
        System.out.println("indent = " + indent);
        return indent;
    }
}
