package com.qf.travel.controller;

import com.qf.travel.pojo.Favorite;
import com.qf.travel.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class FavoriteController {
    @Autowired
    FavoriteService favoriteService;
    /*判断是否存在*/
    @ResponseBody
    @RequestMapping("/loadFavorite")
    public int loadFavorite(String uname,int sid){
        boolean b = favoriteService.loadFavoriteByPri(uname, sid);
        /*如果 b 为真  则存再 已经被收藏*/
        int sc = 1;
        if (b == true){
            return sc;
        }else {
            sc = 0;
            return sc;
        }
    }

    @ResponseBody
    @RequestMapping("/saveFavorite")
    public boolean saveFavorite(String uname ,String sid){
        System.out.println("uname = " + uname);
        System.out.println("sid = " + sid);
        int sids = Integer.parseInt(sid);
        Favorite favorite = new Favorite();
        favorite.setSid(sids);
        favorite.setUname(uname);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date());
        favorite.setFtime(date);
        boolean bs = favoriteService.loadFavoriteByPri(uname,sids);
        System.out.println("bs = " + bs);
        if (bs == true){
            System.out.println("已经存在，请勿重复收藏");
            return false;
        }else {
            boolean b = favoriteService.saveFavorite(favorite);
            return b?true:false;
        }


    }
}
