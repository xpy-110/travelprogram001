package com.qf.travel.service;

import com.qf.travel.pojo.Scenic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ScenicService {
    /*查询所有数据 根据类型 进行分页*/
    public List<Scenic> getScenicByType(String stype);
    /*查询时间排行 每次4个*/
    public List<Scenic> getTopScenicByTime(String stype);
    /*查询人气排行 每次4个数据*/
    public List<Scenic> getTopScenicBySindent(String stype);
    // 模糊查询
    public List<Scenic> getScenicByName(String sname);


    //根据类型查询数据
    public List<Scenic> loadAllByStype(String type,int page,int rows);
    public List<Scenic> loadByStypeSstate(Scenic scenic,int page,int rows);
    //根据id修改状态
    public boolean updateSstateByid(Scenic scenic);
    //根据id删除数据
    public boolean deleteById(int sid);
    //获取某一状态的zuidaye
    public int getMaxpageSstate(Scenic scenic,int rows);
    //根据id修改信息
    public boolean updateByid(Scenic scenic);
    //添加用户
    public boolean saveSecien(Scenic scenic);
    //得到最大页
    public int getMaxpage(String type,int rows);
    //根据id查询
    public Scenic getScenicByid(int sid);
}
