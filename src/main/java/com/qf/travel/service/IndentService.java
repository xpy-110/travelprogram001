package com.qf.travel.service;

import com.qf.travel.pojo.Indent;
import com.qf.travel.pojo.Scenic;

import java.util.List;
import java.util.Map;

public interface IndentService {
    /**
     * 查出所有订单信息
     * @return
     */
    List<Indent> findIndent(String istate,String uanme,int page, int rows);

    /**
     * 查询总的订单数
     * @param rows
     * @return
     */
    public int calcMaxPage(String uname,int rows);
    /**
     * 查询总的订单数
     * @param rows
     * @return
     */
    public int calcMaxPage1(String uname,String istate,int rows);
    /**
     * 模糊查询所有信息
     * */
    List<Indent> queryAll(String uuu);
    /*
     * 模糊查询某个用户的某个信息
     * */
    List<Indent> queryAllByUname(String uname,String uuu);
    /*
     * 单个删除
     * */
    int deleteById(int id);
    /*
     * 批量删除
     * */
    boolean deleteIds(List<Integer> list);

    /**
     * 根据不同状态查出不同订单
     *
     */
    List<Indent> findIndent1(String istate,String uanme,int page, int rows);
    public boolean saveIndent(Indent indent);
    //根据itime查询id
    public int getByItime(String itime);
    //  根据id查数据
    public Indent getIndentById(int id);
    // 根据id改变state
    public boolean updateIstateById(int id);
    // 根据id改变state
    public boolean updateIstateById1(int id);
    //  根据id修改icount
    public int updateIndent(int id,int icount);
    //  根据id修改iprice
    public int updateIndent1(int id,int iprice);
    //  查询订单
    public Scenic getScenicBySid(int sid);

}
