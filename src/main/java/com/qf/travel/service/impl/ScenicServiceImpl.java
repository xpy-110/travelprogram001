package com.qf.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.qf.travel.mapper.ScenicMapper;
import com.qf.travel.pojo.Scenic;
import com.qf.travel.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScenicServiceImpl implements ScenicService {
    @Resource
    private ScenicMapper scenicMapper;
    @Override
    public List<Scenic> getScenicByType(String stype) {
        System.out.println("stype = " + stype);
        List<Scenic> scenics = scenicMapper.getScenicByType(stype);
        System.out.println("scenics = " + scenics);
        return scenics;
    }
    @Override
    public List<Scenic> getTopScenicByTime(String stype) {
        List<Scenic> scenics = scenicMapper.getTopScenicByTime(stype);
        return scenics;
    }

    @Override
    public List<Scenic> getTopScenicBySindent(String stype) {
        List<Scenic> scenics = scenicMapper.getTopScenicBySindent(stype);
        return scenics;
    }

    @Override
    public List<Scenic> getScenicByName(String sname) {
        List<Scenic> scenics = scenicMapper.getScenicByType(sname);
        System.out.println("scenics = " + scenics);
        return scenics;
    }

    @Override
    public List<Scenic> getRouteScenicBySprice(String stype, int page, int rows) {
        PageHelper.startPage(page,rows);
        return scenicMapper.getRouteScenicBySprice(stype);
    }

    @Override
    public List<Scenic> getRouteScenicByScllect(String stype, int page, int rows) {
        PageHelper.startPage(page,rows);
        return scenicMapper.getRouteScenicByScllect(stype);
    }

    @Override
    public List<Scenic> getRouteScenicByStime(String stype, int page, int rows) {
        PageHelper.startPage(page,rows);
        return scenicMapper.getRouteScenicByStime(stype);
    }

    @Override
    public List<Scenic> getRouteScenicBySindent(String stype, int page, int rows) {
        return scenicMapper.getRouteScenicBySindent(stype);
    }

    @Override
    public List<Scenic> getScenicByScllect(int page, int rows) {
        PageHelper.startPage(page,rows);
        return scenicMapper.getScenicByScllect();
    }

    @Override
    public int getAllScenicMaxPage(int rows) {
        int c = scenicMapper.getAllScenicCount();
        int maxPage = c%rows==0?c/rows:c/rows+1;
        System.out.println("maxPage = " + maxPage);
        return maxPage;
    }


    @Override
    public List<Scenic> loadAllByStype(String type,int page,int rows) {
        PageHelper.startPage(page,rows);
        return scenicMapper.loadAllByStype(type);
    }

    @Override
    public List<Scenic> loadByStypeSstate(Scenic scenic, int page, int rows) {
        PageHelper.startPage(page,rows);
        return scenicMapper.loadByStypeSstate(scenic);
    }

    @Override
    public boolean updateSstateByid(Scenic scenic) {
        int c = scenicMapper.updateSstateByid(scenic);
        return c>0?true:false;
    }

    @Override
    public boolean deleteById(int sid) {
        int c = scenicMapper.deleteById(sid);
        return c>0?true:false;
    }

    @Override
    public int getMaxpageSstate(Scenic scenic, int rows) {
        int c = scenicMapper.getSstateCount(scenic);
        int maxPage = c%rows==0?c/rows:c/rows+1;
        return maxPage;
    }

    @Override
    public boolean updateByid(Scenic scenic) {
        int c = scenicMapper.updateByid(scenic);
        return c>0?true:false;
    }

    @Override
    public boolean saveSecien(Scenic scenic) {
        int c = scenicMapper.saveSecien(scenic);
        return c>0?true:false;
    }

    @Override
    public int getMaxpage(String type,int rows) {
        int c = scenicMapper.getCountBytype(type);
        int maxPage = c%rows==0?c/rows:c/rows+1;
        return maxPage;
    }

    @Override
    public Scenic getScenicByid(int sid) {
        return scenicMapper.getScenicByid(sid);
    }

    @Override
    public List<Scenic> inquireScenic(String stype, String uuu) {
        Map<String,Object> map = new HashMap<>();
        map.put("stype",stype);
        map.put("uuu",uuu);
        return scenicMapper.inquireScenic(map);
    }
}
