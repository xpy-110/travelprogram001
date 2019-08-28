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
    public List<Indent> findIndent(String uname);

    /**
     * 查询订单总个数
     * @return
     */
    public int getTotalCount(String uname);
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
}
