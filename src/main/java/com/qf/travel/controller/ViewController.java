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
    public String myfavorite(){
        return "myfavorite";
    }

    @RequestMapping("indent")
    public String indent(){
        return "indent";
    }

    @RequestMapping("indent_detail")
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
}
