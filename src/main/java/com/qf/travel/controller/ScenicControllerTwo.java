package com.qf.travel.controller;

import com.qf.travel.pojo.Scenic;
import com.qf.travel.service.ScenicService;
import com.qf.travel.utils.ScienceUtiles;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ScenicControllerTwo {
    //路线管理
    @Autowired
    private ScenicService scenicService;
    @RequiresPermissions(value = {"pathManage"})
    @RequestMapping("/RouteMaintain")
    public String RouteMaintain(@RequestParam(required = false,defaultValue = "1")int page,
                                  @RequestParam(required = false,defaultValue = "10")int rows,
                                  Model model){
        String type = "路线";
        int sstate = 1;
        Scenic scenic = new Scenic();
        scenic.setStype(type);
        scenic.setSstate(sstate);
        int maxPage = scenicService.getMaxpageSstate(scenic,rows);
        if (page > maxPage){
            page = 1;
        }
        if (page < 1){
            page = maxPage;
        }
        List<Scenic> scenics = scenicService.loadByStypeSstate(scenic,page,rows);
        model.addAttribute("scenics",scenics);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPage);
        return "Route";
    }
    //删除路线信息
    @RequiresPermissions(value = {"pathdelete"})
    @RequestMapping("/deleteRoute")
    public String deleteRoute(int Sid){
        boolean bool = scenicService.deleteById(Sid);
        return bool?"redirect:RouteMaintain":"error";
    }
    //批量删除
    @RequiresPermissions(value = {"pathdelete"})
    @RequestMapping("/deleteRoute1")
    public String deleteRoute1(String ids){
        String[] sids = ids.split("-");
        boolean bool = false;
        for (int i = 0; i < sids.length; i++) {
            bool = scenicService.deleteById(Integer.parseInt(sids[i]));
        }
        return bool?"redirect:RouteMaintain":"error";
    }
    //修改
    @RequiresPermissions(value = {"pathupdate"})
    @RequestMapping("/updateRoute")
    public String updateRoute(int Sid,Model model){
        Scenic scenic = scenicService.getScenicByid(Sid);
        model.addAttribute("scenic",scenic);
        return "route_1";
    }
    @RequiresPermissions(value = {"pathupdate"})
    @ResponseBody
    @RequestMapping("/updateRoute1")
    public boolean updateRoute1(int sid, String sname, int sindent, int scllect, int scomment,
                                  String scity, double sprice, MultipartFile simgs,
                                  String sfeature, HttpServletRequest request){
        String stype = "路线";
        String filepath = ScienceUtiles.saveImg(simgs,request);
        Scenic scenic = ScienceUtiles.buildScience(sid,sname,sindent,scllect,scomment,scity,sprice,filepath,stype,sfeature);
        boolean bool = scenicService.updateByid(scenic);
        return bool;
    }
    //是否下架
    @RequiresPermissions(value = {"pathManage"})
    @RequestMapping("/isRoute")
    public String isRoute(int Sid){
        int sstate = 0;
        Scenic scenic = new Scenic();
        scenic.setSid(Sid);
        scenic.setSstate(sstate);
        boolean bool = scenicService.updateSstateByid(scenic);
        return bool?"redirect:RouteMaintain":"error";
    }
    //路线信息审核
    @RequiresPermissions(value = {"pathAud"})
    @RequestMapping("/Routeexamine")
    public String Routeexamine(@RequestParam(required = false,defaultValue = "1")int page,
                                 @RequestParam(required = false,defaultValue = "10")int rows,
                                 Model model){
        String type = "路线";
        int sstate = 0;
        Scenic scenic = new Scenic();
        scenic.setStype(type);
        scenic.setSstate(sstate);
        int maxPage = scenicService.getMaxpageSstate(scenic,rows);
        if (page > maxPage){
            page = 1;
        }
        if (page < 1){
            page = maxPage;
        }
        List<Scenic> scenics = scenicService.loadByStypeSstate(scenic,page,rows);
        model.addAttribute("scenics",scenics);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPage);
        return "route_3";
    }
    //删除
    @RequiresPermissions(value = {"pathAud"})
    @RequestMapping("/deleteRouteexamine")
    public String deleteRouteexamine(int Sid){
        boolean bool = scenicService.deleteById(Sid);
        return bool?"redirect:Routeexamine":"error";
    }
    //是否上架
    @RequiresPermissions(value = {"pathAud"})
    @RequestMapping("/updateRouteexamine")
    public String updateRouteexamine(int Sid){
        int sstate = 1;
        Scenic scenic = new Scenic();
        scenic.setSid(Sid);
        scenic.setSstate(sstate);
        boolean bool = scenicService.updateSstateByid(scenic);
        return bool?"redirect:Routeexamine":"error";
    }
    //增加路线信息
    @RequiresPermissions(value = {"addPath"})
    @RequestMapping("/addRoute")
    public String addRoute(){
        return "route_2";
    }
    @ResponseBody
    @RequestMapping("/addRoute1")
    public boolean addRoute1(String sname,int sindent,int scllect,int scomment,
                               String scity,double sprice,MultipartFile simgs,
                               String sfeature,HttpServletRequest request){
        String stype = "路线";
        int sstate = 0;
        String filepath = ScienceUtiles.saveImg(simgs,request);
        Scenic scenic = ScienceUtiles.buildScience1(sname,sindent,scllect,scomment,scity,sprice,filepath,stype,sfeature,sstate);
        System.out.println(scenic);
        boolean bool = scenicService.saveSecien(scenic);
        return bool;
    }
    //搜索路线信息
    @RequiresPermissions(value = {"pathAud"})
    @RequestMapping("/RouteQuery")
    public String RouteQuery(String uuu,Model model){
        List<Scenic> scenics = scenicService.inquireScenic("路线",uuu);
        model.addAttribute("scenics",scenics);
        return "routequery";
    }
}
