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
import java.util.List;

@Controller
public class ScenicControllerOne {
    //景点管理
    @Autowired
    private ScenicService scenicService;
    @RequestMapping("/ScienceMaintain")
    public String ScienceMaintain(@RequestParam(required = false,defaultValue = "1")int page,
                                @RequestParam(required = false,defaultValue = "10")int rows,
                                Model model){
        String type = "景点";
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
        return "Scenic";
    }
    //删除景点信息
    @RequestMapping("/deleteScience")
    public String deleteScience(int Sid){
        boolean bool = scenicService.deleteById(Sid);
        return bool?"redirect:ScienceMaintain":"error";
    }
    //批量删除
    @RequestMapping("/deleteSciences")
    public String deleteSciences(String ids){
        String[] sids = ids.split("-");
        boolean bool = false;
        for (int i = 0; i < sids.length; i++) {
            bool = scenicService.deleteById(Integer.parseInt(sids[i]));
        }
        return bool?"redirect:ScienceMaintain":"error";
    }
    //修改
    @RequestMapping("/updateScience")
    public String updateScience(int Sid,Model model){
        Scenic scenic = scenicService.getScenicByid(Sid);
        model.addAttribute("scenic",scenic);
        return "scenic_1";
    }
    @ResponseBody
    @RequestMapping("/updateScience1")
    public boolean updateScience1(int sid, String sname, int sindent, int scllect, int scomment,
                                String scity, double sprice, MultipartFile simgs,
                                String sfeature, HttpServletRequest request){
        String stype = "景点";
        String filepath = ScienceUtiles.saveImg(simgs,request);
        Scenic scenic = ScienceUtiles.buildScience(sid,sname,sindent,scllect,scomment,scity,sprice,filepath,stype,sfeature);
        boolean bool = scenicService.updateByid(scenic);
        return bool;
    }
    //是否下架
    @RequestMapping("/isScience")
    public String isScience(int Sid){
        int sstate = 0;
        Scenic scenic = new Scenic();
        scenic.setSid(Sid);
        scenic.setSstate(sstate);
        boolean bool = scenicService.updateSstateByid(scenic);
        return bool?"redirect:ScienceMaintain":"error";
    }
    //景点信息审核
    @RequestMapping("/Scienceexamine")
    public String Scienceexamine(@RequestParam(required = false,defaultValue = "1")int page,
                               @RequestParam(required = false,defaultValue = "10")int rows,
                               Model model){
        String type = "景点";
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
        return "scenic_3";
    }
    //删除
    @RequestMapping("/deleteScienceexamine")
    public String deleteScienceexamine(int Sid){
        boolean bool = scenicService.deleteById(Sid);
        return bool?"redirect:Scienceexamine":"error";
    }
    //是否上架
    @RequestMapping("/updateScienceexamine")
    public String updateScienceexamine(int Sid){
        int sstate = 1;
        Scenic scenic = new Scenic();
        scenic.setSid(Sid);
        scenic.setSstate(sstate);
        boolean bool = scenicService.updateSstateByid(scenic);
        return bool?"redirect:Scienceexamine":"error";
    }
    //增加景点信息
    @RequestMapping("/addScience")
    public String addScience(){
        return "scenic_2";
    }
    @ResponseBody
    @RequestMapping("/addScience1")
    public boolean addScience1(String sname,int sindent,int scllect,int scomment,
                               String scity,double sprice,MultipartFile simgs,
                               String sfeature,HttpServletRequest request){
        String stype = "景点";
        int sstate = 0;
        String filepath = ScienceUtiles.saveImg(simgs,request);
        Scenic scenic = ScienceUtiles.buildScience1(sname,sindent,scllect,scomment,scity,sprice,filepath,stype,sfeature,sstate);
        System.out.println(scenic);
        boolean bool = scenicService.saveSecien(scenic);
        return bool;
    }
    //搜索景点信息
    @RequestMapping("/ScienceQuery")
    public String ScienceQuery(String uuu,Model model){
        List<Scenic> scenics = scenicService.inquireScenic("景点",uuu);
        model.addAttribute("scenics",scenics);
        return "scenicquery";
    }

}
