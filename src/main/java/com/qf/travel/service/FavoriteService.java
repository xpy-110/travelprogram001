package com.qf.travel.service;

import com.qf.travel.mapper.FavoriteMapper;
import com.qf.travel.pojo.Favorite;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public interface FavoriteService {
    public boolean loadFavoriteByPri(String uname ,int sid);
    public boolean saveFavorite(Favorite favorite);
}
