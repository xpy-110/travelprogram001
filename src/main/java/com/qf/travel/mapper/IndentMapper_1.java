package com.qf.travel.mapper;

import com.qf.travel.pojo.Indent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IndentMapper_1 {
    List<Indent> queryAll();
    int getTotalCount(String uname);
    List<Indent> findIndent(String uname);
    int IndentManUp(Map<String,Object> map);
    int getAllCount();
    List<Indent> loadAll(String istate);
    Indent getUnameById(int id);

    int getCountStyIsta(Map<String,String> map);

    List<Indent> loadAll11();
}
