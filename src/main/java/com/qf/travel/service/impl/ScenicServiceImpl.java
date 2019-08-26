package com.qf.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.qf.travel.mapper.ScenicMapper;
import com.qf.travel.pojo.Scenic;
import com.qf.travel.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenicServiceImpl implements ScenicService {
    @Autowired
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
}
