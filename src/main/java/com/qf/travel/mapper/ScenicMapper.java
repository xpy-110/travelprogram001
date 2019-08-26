package com.qf.travel.mapper;

import com.qf.travel.pojo.Scenic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ScenicMapper {
    //  根据类型查找所有的景点、路线、酒店信息
    public List<Scenic> getScenicByType(String stype);

    //根据类型查询数据
    public List<Scenic> loadAllByStype(String type);
    public int getCountBytype(String type);
    //根据状态类型查询信息
    public List<Scenic> loadByStypeSstate(@Param("scenic")Scenic scenic);
    //根据id修改状态
    public int updateSstateByid(@Param("scenic")Scenic scenic);
    //根据id sstate查询数量
    public int getSstateCount(@Param("scenic")Scenic scenic);
    //根据id删除数据
    public int deleteById(int sid);
    //根据id修改信息
    public int updateByid(@Param("scenic")Scenic scenic);
    //添加用户
    public int saveSecien(@Param("scenic")Scenic scenic);
    //根据id查询
    public Scenic getScenicByid(int sid);
}
