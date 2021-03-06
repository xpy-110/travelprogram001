package com.qf.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping("route_detail")
    public String route_detail(){
        return "route_detail";
    }


    @RequestMapping("myfavorite")
    public String myfavorite(String uname){
        System.out.println("uname = " + uname);
        return "myfavorite";
    }

    @RequestMapping("indent")
    public String indent(){
        String istate="已完成订单";
        return "redirect:findIndent?istate="+istate;
    }
    @RequestMapping("indent_1")
    public String indent_1(){
        String istate="待付款";
        return "redirect:findIndent1?istate="+istate;
    }
    @RequestMapping("indent_2")
    public String indent_2(){
        String istate="已付款";
        return "redirect:findIndent1?istate="+istate;
    }
    @RequestMapping("indent_detailview")
    public String indent_detail(){
        return "indent_detail";
    }

    @RequestMapping("mydata")
    public String mydata(){
        return "mydata";
    }
    @RequestMapping("personal_center")
    public String personal_center(){
        return "personal_center";
    }
    @RequestMapping("favoriterank")
    public String favoriterank(){
        return "favoriterank";
    }
    @RequestMapping("evaluate")
    public String evaluate(){
        return "evaluate";
    }

}
