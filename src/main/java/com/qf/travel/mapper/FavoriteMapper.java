package com.qf.travel.mapper;


import com.qf.travel.pojo.Favorite;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface FavoriteMapper {

    public Favorite loadFavoriteByPri(Map<String ,Object> map);
    public int saveFavorite(Favorite favorite);
}
