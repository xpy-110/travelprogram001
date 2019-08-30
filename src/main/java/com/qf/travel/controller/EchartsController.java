package com.qf.travel.controller;

import com.qf.travel.pojo.NumEcharts;
import com.qf.travel.pojo.Numbb;
import com.qf.travel.service.IndentService_1;
import com.qf.travel.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EchartsController {
    @Autowired
    private ScenicService scenicService;
    @Autowired
    private IndentService_1 indentService_1;
    @ResponseBody
    @RequestMapping("/pieMap")
    public List<NumEcharts> pieMap(){
        System.out.println(111);
        List<NumEcharts> list = new ArrayList<>();
        int hotelCount = scenicService.getCountBytype("酒店");
        int scenicCount = scenicService.getCountBytype("景点");
        int routeCount = scenicService.getCountBytype("路线");
        list.add(new NumEcharts(hotelCount,"酒店"));
        list.add(new NumEcharts(scenicCount,"景点"));
        list.add(new NumEcharts(routeCount,"旅游路线"));
        System.out.println(list);
        return list;
    }
    @ResponseBody
    @RequestMapping("/hotelpei")
    public List<NumEcharts> hotelpei(){
        System.out.println(111);
        List<NumEcharts> list = new ArrayList<>();
        String iii = "已完成订单";
        String rrr = "已付款";
        String ppp = "待付款";
        int htole1 = indentService_1.getCountStyIsta(iii,"酒店");
        int htole2 = indentService_1.getCountStyIsta(rrr,"酒店");
        int htole3 = indentService_1.getCountStyIsta(ppp,"酒店");
        list.add(new NumEcharts(htole1,iii));
        list.add(new NumEcharts(htole2,rrr));
        list.add(new NumEcharts(htole3,ppp));
        System.out.println(list);
        return list;
    }
    @ResponseBody
    @RequestMapping("/scenicpei")
    public List<NumEcharts> scenicpei(){
        System.out.println(111);
        List<NumEcharts> list = new ArrayList<>();
        String iii = "已完成订单";
        String rrr = "已付款";
        String ppp = "待付款";
        int htole1 = indentService_1.getCountStyIsta(iii,"景点");
        int htole2 = indentService_1.getCountStyIsta(rrr,"景点");
        int htole3 = indentService_1.getCountStyIsta(ppp,"景点");
        list.add(new NumEcharts(htole1,iii));
        list.add(new NumEcharts(htole2,rrr));
        list.add(new NumEcharts(htole3,ppp));
        System.out.println(list);
        return list;
    }
    @ResponseBody
    @RequestMapping("/routepei")
    public List<NumEcharts> routepei(){
        System.out.println(111);
        List<NumEcharts> list = new ArrayList<>();
        String iii = "已完成订单";
        String rrr = "已付款";
        String ppp = "待付款";
        int htole1 = indentService_1.getCountStyIsta(iii,"路线");
        int htole2 = indentService_1.getCountStyIsta(rrr,"路线");
        int htole3 = indentService_1.getCountStyIsta(ppp,"路线");
        list.add(new NumEcharts(htole1,iii));
        list.add(new NumEcharts(htole2,rrr));
        list.add(new NumEcharts(htole3,ppp));
        System.out.println(list);
        return list;
    }
}
