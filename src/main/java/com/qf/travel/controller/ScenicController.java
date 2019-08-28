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
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ScenicController {
    @Autowired
    private ScenicService scenicService;

    /*首页显示景点*/
    @RequestMapping("getScenicByType")
    public String getScenicByType(String stype, Model model){
        System.out.println("stype = " + stype);
        List<Scenic> scenics = scenicService.getScenicByType(stype);
        System.out.println("conrtroller:: scenics = " + scenics);
        model.addAttribute("scenics",scenics);
        return "memeber";
    }
    /*动态生成首页景点信息*/
    @ResponseBody
    @RequestMapping("/getAllScenicByType")
    public List<Scenic> getAllScenicByType(String stype, Model model){
        System.out.println("stype = " + stype);
        String stypes = "景点";
        List<Scenic> scenics = scenicService.getScenicByType(stypes);
        System.out.println("Scontroller:: scenics = " + scenics);
        model.addAttribute("scenics",scenics);
        return scenics;
    }
    /*动态生成首页酒店信息*/
    @ResponseBody
    @RequestMapping("/getHotelScenicByType")
    public List<Scenic> getHotelScenicByType(String stype, Model model){
        System.out.println("stype = " + stype);
        String stypes = "酒店";
        List<Scenic> scenics = scenicService.getScenicByType(stypes);
        System.out.println("Scontroller:: scenics = " + scenics);
        model.addAttribute("scenics",scenics);
        return scenics;
    }
    /*动态生成首页路线信息*/
    @ResponseBody
    @RequestMapping("/getPathScenicByType")
    public List<Scenic> getPathScenicByType(){
        String stypes = "路线";
        List<Scenic> scenics = scenicService.getScenicByType(stypes);
        System.out.println("Scontroller:: scenics = " + scenics);
        return scenics;
    }
    /*动态生成top信息，最新旅游*/
    @ResponseBody
    @RequestMapping("/getTopScenicByTime")
    public List<Scenic> getTopScenicByTime(){
        String stypes = "景点";
        List<Scenic> scenics = scenicService.getTopScenicByTime(stypes);
        return scenics;
    }

    /*动态生成top信息，人气旅游*/
    @ResponseBody
    @RequestMapping("/getTopScenicByType")
    public List<Scenic> getTopScenicByType(String stype){
        System.out.println("人气stype = " + stype);
        String stypes = "景点";
        List<Scenic> scenics = scenicService.getTopScenicBySindent(stypes);
        System.out.println("scenics = " + scenics);
        return scenics;
    }

    /*动态生成top信息，路线旅游*/
    @ResponseBody
    @RequestMapping("/getTopPathScenicByType")
    public List<Scenic> getTopPathScenicByType(String stype){
        String stypes = "路线";
        System.out.println("stypes = " + stypes);
        List<Scenic> scenics = scenicService.getTopScenicBySindent(stypes);
        System.out.println("scenics = " + scenics);
        return scenics;
    }
    /*根据id查询scenic信息发送至详情界面*/
    @RequestMapping("/gotoRouteDetail")
    public String gotoIndent_detail(String sid ,Model model){
        int i = Integer.parseInt(sid);
        System.out.println("i = " + i);
        Scenic scenic = scenicService.getScenicByid(i);
        System.out.println("scenic = " + scenic);
        model.addAttribute(scenic);
        return "route_detail";
    }

    /*跳往seek界面*/
    @RequestMapping("/seek")
    public String gotoSeek(){

        return "seek";
    }

    /*跳往route_list界面*/
    @RequestMapping("/routelist")
    public String route_list(@RequestParam(required = false,defaultValue = "1")int page,
                             @RequestParam(required = false,defaultValue = "8")int rows,
                             Model model){
        String stype = "酒店";
        int maxPage = scenicService.getMaxpage(stype,rows);
        if (page > maxPage){
            page = 1;
        }
        if (page < 1){
            page = maxPage;
        }
        String sign = "1";
        System.out.println("maxPage = " + maxPage);
        System.out.println("page = " + page);
        List<Scenic> scenics = scenicService.loadAllByStype(stype, page, rows);
        model.addAttribute("scenics",scenics);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("sign",sign);
        return "route_list";
    }
    /*route_list 价格排行*/

    @RequestMapping("/routelistByPrice")
    public String route_listByPrice(@RequestParam(required = false,defaultValue = "1")int page,
                             @RequestParam(required = false,defaultValue = "8")int rows,
                             Model model){
        String stype = "酒店";
        int maxPage = scenicService.getMaxpage(stype,rows);
        if (page > maxPage){
            page = 1;
        }
        if (page < 1){
            page = maxPage;
        }
        String sign = "2";
        System.out.println("maxPage = " + maxPage);
        System.out.println("page = " + page);
        List<Scenic> scenics = scenicService.getRouteScenicBySprice(stype, page, rows);
        model.addAttribute("scenics",scenics);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("sign",sign);
        return "route_list";
    }
    /*route_list 收藏排行*/
    @RequestMapping("/routelistByScllect")
    public String route_listByScllect(@RequestParam(required = false,defaultValue = "1")int page,
                                    @RequestParam(required = false,defaultValue = "8")int rows,
                                    Model model){
        String stype = "酒店";
        int maxPage = scenicService.getMaxpage(stype,rows);
        if (page > maxPage){
            page = 1;
        }
        if (page < 1){
            page = maxPage;
        }
        String sign = "3";
        System.out.println("maxPage = " + maxPage);
        System.out.println("page = " + page);
        List<Scenic> scenics = scenicService.getRouteScenicByScllect(stype, page, rows);
        model.addAttribute("scenics",scenics);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("sign",sign);
        return "route_list";
    }
    /*route_list 最新排行*/
    @RequestMapping("/routelistByStime")
    public String route_listByStime(@RequestParam(required = false,defaultValue = "1")int page,
                                      @RequestParam(required = false,defaultValue = "8")int rows,
                                      Model model){
        String stype = "酒店";
        int maxPage = scenicService.getMaxpage(stype,rows);
        if (page > maxPage){
            page = 1;
        }
        if (page < 1){
            page = maxPage;
        }
        String sign = "4";
        System.out.println("maxPage = " + maxPage);
        System.out.println("page = " + page);
        List<Scenic> scenics = scenicService.getRouteScenicByStime(stype, page, rows);
        model.addAttribute("scenics",scenics);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("sign",sign);
        return "route_list";
    }
    /*route_list 销量排行*/
    @RequestMapping("/routelistBySindent")
    public String route_listBySindent(@RequestParam(required = false,defaultValue = "1")int page,
                                    @RequestParam(required = false,defaultValue = "8")int rows,
                                    Model model){
        String stype = "酒店";
        int maxPage = scenicService.getMaxpage(stype,rows);
        if (page > maxPage){
            page = 1;
        }
        if (page < 1){
            page = maxPage;
        }
        String sign = "5";
        System.out.println("maxPage = " + maxPage);
        System.out.println("page = " + page);
        List<Scenic> scenics = scenicService.getRouteScenicBySindent(stype, page, rows);
        model.addAttribute("scenics",scenics);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("sign",sign);
        return "route_list";
    }
    /*favoriterank 收藏排行*/
    @RequestMapping("/getScenicByScllect")
    public String getScenicByScllect(@RequestParam(required = false,defaultValue = "1")int page,
                                      @RequestParam(required = false,defaultValue = "8")int rows,
                                      Model model){
        int size = 1;
        int maxPage = scenicService.getAllScenicMaxPage(rows);
        if (page > maxPage){
            page = 1;
        }
        if (page < 1){
            page = maxPage;
        }
        List<Scenic> scenics = scenicService.getScenicByScllect(page, rows);
        model.addAttribute("scenics",scenics);
        model.addAttribute("currentPage",page);
        model.addAttribute("maxPage",maxPage);
        model.addAttribute("size",size);
        return "favoriterank";
    }
    //  seek 模糊查询
    @RequestMapping("/queryAllScenic")
    public String queryAllScenic(String xxx,Model model){
        int mess;
        String awarm;
        System.out.println("xxx = " + xxx);
        if (xxx != null){
            List<Scenic> scenics = scenicService.queryAllScenic(xxx);
            if (scenics.size() == 0){
                mess = 2;
                awarm = "没有找到需要的信息";
                System.out.println("mess = " + mess);
                System.out.println("awarm = " + awarm);
                model.addAttribute("awarm",awarm);
                model.addAttribute("mess",mess);
            }else {
                mess = 1;
                System.out.println("mess = " + mess);
                System.out.println("模糊查出的scenics = " + scenics);
                model.addAttribute("mess",mess);
                model.addAttribute("scenics",scenics);
            }
            return "seek";
        }else {
            mess =2 ;
            awarm = "请不要输入空的信息就搜索";
            model.addAttribute("awarm",awarm);
            model.addAttribute("mess",mess);
            return "seek";
        }
    }

    //酒店管理
    //酒店维护
    @RequiresPermissions(value = {"groMamage"})
    @RequestMapping("/hotelMaintain")
    public String hotelMaintain(@RequestParam(required = false,defaultValue = "1")int page,
                                @RequestParam(required = false,defaultValue = "10")int rows,
                                Model model){
        String type = "酒店";
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
        return "hotel";
    }
    //删除酒店信息
    @RequiresPermissions(value = {"hoteldelete"})
    @RequestMapping("/deleteHodel")
    public String deleteHodel(int Sid){
        boolean bool = scenicService.deleteById(Sid);
        return bool?"redirect:hotelMaintain":"error";
    }
    //批量删除
    @RequiresPermissions(value = {"hoteldelete"})
    @RequestMapping("/deleteHodels")
    public String deleteHodels(String ids){
        String[] sids = ids.split("-");
        boolean bool = false;
        for (int i = 0; i < sids.length; i++) {
            bool = scenicService.deleteById(Integer.parseInt(sids[i]));
        }
        return bool?"redirect:hotelMaintain":"error";
    }
    //修改
    @RequiresPermissions(value = {"hotelupdate"})
    @RequestMapping("/updateHodel")
    public String updateHodel(int Sid,Model model){
        Scenic scenic = scenicService.getScenicByid(Sid);
        model.addAttribute("scenic",scenic);
        return "hodel_1";
    }
    @ResponseBody
    @RequiresPermissions(value = {"hotelupdate"})
    @RequestMapping("/updateHodel1")
    public boolean updateHodel1(int sid,String sname,int sindent,int scllect,int scomment,
                                String scity,double sprice,MultipartFile simgs,
                                String sfeature,HttpServletRequest request){
        String stype = "酒店";
        String filepath = ScienceUtiles.saveImg(simgs,request);
        Scenic scenic = ScienceUtiles.buildScience(sid,sname,sindent,scllect,scomment,scity,sprice,filepath,stype,sfeature);
        boolean bool = scenicService.updateByid(scenic);
        return bool;
    }
    //是否下架
    @RequiresPermissions(value = {"groMamage"})
    @RequestMapping("/ishotel")
    public String ishotel(int Sid){
        int sstate = 0;
        Scenic scenic = new Scenic();
        scenic.setSid(Sid);
        scenic.setSstate(sstate);
        boolean bool = scenicService.updateSstateByid(scenic);
        return bool?"redirect:hotelMaintain":"error";
    }
    //酒店信息审核
    @RequiresPermissions(value = {"groAud"})
    @RequestMapping("/hotelexamine")
    public String hotelexamine(@RequestParam(required = false,defaultValue = "1")int page,
                                @RequestParam(required = false,defaultValue = "10")int rows,
                                Model model){
        String type = "酒店";
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
        return "hotel_3";
    }
    //删除
    @RequiresPermissions(value = {"groAud"})
    @RequestMapping("/deletehotelexamine")
    public String deletehotelexamine(int Sid){
        boolean bool = scenicService.deleteById(Sid);
        return bool?"redirect:hotelexamine":"error";
    }
    //是否上架
    @RequiresPermissions(value = {"groAud"})
    @RequestMapping("/updatehotelexamine")
    public String updatehotelexamine(int Sid){
        int sstate = 1;
        Scenic scenic = new Scenic();
        scenic.setSid(Sid);
        scenic.setSstate(sstate);
        boolean bool = scenicService.updateSstateByid(scenic);
        return bool?"redirect:hotelexamine":"error";
    }
    //增加酒店信息
    @RequiresPermissions(value = {"addGro"})
    @RequestMapping("/addHotel")
    public String addScience(){
        return "hodel_2";
    }
    @ResponseBody
    @RequiresPermissions(value = {"addGro"})
    @RequestMapping("/addHotel1")
    public boolean addScience1(String sname,int sindent,int scllect,int scomment,
                               String scity,double sprice,MultipartFile simgs,
                               String sfeature,HttpServletRequest request){
        String stype = "酒店";
        int sstate = 0;
        String filepath = ScienceUtiles.saveImg(simgs,request);
        Scenic scenic = ScienceUtiles.buildScience1(sname,sindent,scllect,scomment,scity,sprice,filepath,stype,sfeature,sstate);
        System.out.println(scenic);
        boolean bool = scenicService.saveSecien(scenic);
        return bool;
    }
    //搜索酒店信息
    @RequiresPermissions(value = {"groMamage"})
    @RequestMapping("/hotelQuery")
    public String hotelQuery(String uuu,Model model){
        List<Scenic> scenics = scenicService.inquireScenic("酒店",uuu);
        model.addAttribute("scenics",scenics);
        return "hotelquery";
    }

}
