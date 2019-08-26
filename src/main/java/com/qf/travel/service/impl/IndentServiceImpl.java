package com.qf.travel.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qf.travel.mapper.IndentMapper;
import com.qf.travel.pojo.Indent;
import com.qf.travel.service.IndentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        return indent;
    }
    /**
     * 得出总页数
     * @param rows
     * @return
     */
    @Override
    public int calcMaxPage(String uname,int rows) {
        int count=indentMapper.getTotalCount(uname);
        System.out.println(count);
        return count%rows==0?count/rows:count/rows+1;
    }
}
