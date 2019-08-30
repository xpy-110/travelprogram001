package com.qf.travel.service.impl;

import com.qf.travel.mapper.FavoriteMapper;
import com.qf.travel.pojo.Favorite;
import com.qf.travel.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    FavoriteMapper favoriteMapper;

    @Override
    public boolean loadFavoriteByPri(String uname, int sid) {
        Map<String,Object> map = new HashMap<>();
        map.put("uname",uname);
        map.put("sid",sid);
        Favorite favorite = favoriteMapper.loadFavoriteByPri(map);

        return favorite != null ?true :false;
    }

    @Override
    public boolean saveFavorite(Favorite favorite) {
        int i = favoriteMapper.saveFavorite(favorite);
        return i >0?true:false;
    }
}
