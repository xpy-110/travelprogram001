package com.qf.travel.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qf.travel.mapper.IndentMapper;
import com.qf.travel.pojo.Indent;
import com.qf.travel.pojo.Scenic;
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
    public List<Indent> findIndent(String istate,String uname, int page, int rows) {
        PageHelper.startPage(page, rows);
        Map<String,String> map=new HashMap<>();
        map.put("uname",uname);
        map.put("istate",istate);
        System.out.println("map = " + map);
        List<Indent> indent = indentMapper.findIndent1(map);
        System.out.println("indent = " + indent);
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
        map.put("uname",uname);
        map.put("istate",istate);
        System.out.println("map = " + map);
        List<Indent> indent = indentMapper.findIndent1(map);
        System.out.println("indent = " + indent);
        return indent;
    }

    @Override
    public boolean saveIndent(Indent indent) {

        int count= indentMapper.saveindent(indent);
        return count>0?true:false;
    }

    @Override
    public int getByItime(String itime) {
        return indentMapper.getByItime(itime);
    }

    @Override
    public Indent getIndentById(int id) {
        Indent indent = indentMapper.getIndentById(id);
        return indent;
    }

    @Override
    public boolean updateIstateById(int id) {
        int i = indentMapper.updateIstateById(id);
        return i>0?true:false;
    }
    @Override
    public boolean updateIstateById1(int id) {
        System.out.println("0000000000000000000000"+id);
        int i = indentMapper.updateIstateById1(id);
        return i>0?true:false;
    }

    @Override
    public int updateIndent(int id, int icount) {
        int i = indentMapper.updateIndent(id, icount);
        System.out.println("i = " + i);
        return i;
    }@Override
    public int updateIndent1(int id, int iprice) {
        int i = indentMapper.updateIndent1(id, iprice);
        System.out.println("i = " + i);
        return i;
    }


    @Override
    public Scenic getScenicBySid(int sid) {
        Scenic scenic = indentMapper.getScenicBySid(sid);
        return scenic;
    }
}
