package com.qf.travel.mapper;

import com.qf.travel.pojo.Indent;
import org.apache.ibatis.annotations.Mapper;

import javax.annotation.Resource;
import java.util.List;

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
}
