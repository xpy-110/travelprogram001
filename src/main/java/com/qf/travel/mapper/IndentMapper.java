package com.qf.travel.mapper;

import com.qf.travel.pojo.Indent;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface IndentMapper {
    /**
     * 查出所有订单信息
     * @return
     */
    List<Indent> findIndent(Map<String,String> map);

    /**
     * 查询订单总个数
     * @return
     */
    int getTotalCount(String uname);
    /**
     * 查询订单总个数
     * @return
     */
    int getTotalCount1(String uname,String istate);
    /**
    * 模糊查询所有信息
    * */
    List<Indent> queryAll(String uuu);
    /*
    * 模糊查询某个用户的某个信息
    * */
    List<Indent> queryAllByUname(Map<String,String> map);
    /*
    * 单个删除
    * */
    int deleteById(int id);
    /*
    * 批量删除
    * */
    int deleteIds(List<Integer> list);

    /**
     * 根据不同状态查出不同订单
     * @param istate
     * @return
     */
    List<Indent> findIndent1(Map<String,String> map);
    /*添加新的 订单*/
    public int saveindent(Indent indent);
    /*根据uname 和 下单时间 得查出 订单*/
    public Indent getindent(Map<String,String> map);
    //根据itime查询id
    public int getByItime(String itime);
    //  根据id 获取indent数据
    public Indent getIndentById(int id);
    //  根据id修改istate
    public int updateIstateById(int id);

}
