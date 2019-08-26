package com.qf.travel.controller;

import com.qf.travel.pojo.Scenic;
import com.qf.travel.service.ScenicService;
import com.qf.travel.utils.ScienceUtiles;
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

    //酒店管理
    //酒店维护
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
    @RequestMapping("/deleteHodel")
    public String deleteHodel(int Sid){
        boolean bool = scenicService.deleteById(Sid);
        return bool?"redirect:hotelMaintain":"error";
    }
    //批量删除
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
    @RequestMapping("/updateHodel")
    public String updateHodel(int Sid,Model model){
        Scenic scenic = scenicService.getScenicByid(Sid);
        model.addAttribute("scenic",scenic);
        return "hodel_1";
    }
    @ResponseBody
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
    @RequestMapping("/deletehotelexamine")
    public String deletehotelexamine(int Sid){
        boolean bool = scenicService.deleteById(Sid);
        return bool?"redirect:hotelexamine":"error";
    }
    //是否上架
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
    @RequestMapping("/addHotel")
    public String addScience(){
        return "hodel_2";
    }
    @ResponseBody
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



    //路线管理

}
