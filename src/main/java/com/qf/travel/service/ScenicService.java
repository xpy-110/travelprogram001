package com.qf.travel.service;

import com.qf.travel.pojo.Scenic;

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
    // route——list 进行价格排行
    public List<Scenic> getRouteScenicBySprice(String stype,int page,int rows);
    // route——list 进行收藏排行
    public List<Scenic> getRouteScenicByScllect(String stype,int page,int rows);
    // route——list 进行时间排行
    public List<Scenic> getRouteScenicByStime(String stype,int page,int rows);
    // route——list 进行销量排行
    public List<Scenic> getRouteScenicBySindent(String stype,int page,int rows);
    // favoriterank 收藏排行
    public List<Scenic> getScenicByScllect(int page,int rows);
    //  查询snice所有数量、
    public int getAllScenicMaxPage(int rows);
    //  模糊查询所有
    public List<Scenic> queryAllScenic(String xxx);
    //  我的收藏查询
    public List<Scenic> getScenicByUname(String uname,int page ,int rows);


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
    // 模糊查询
    List<Scenic> inquireScenic(String stype,String uuu);
}
