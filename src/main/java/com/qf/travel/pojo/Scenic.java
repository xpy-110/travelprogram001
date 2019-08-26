package com.qf.travel.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Scenic implements Serializable {
    private int sid; // id
    private String sname;   // 景点名称信息
    private int sindent;    // 订单数
    private int scllect;    //  收藏数
    private int scomment;    // 评论次数
    private String scity;   //  所属城市
    private double sprice;   // 价格
    private String simgs;   //  图片简介
    private String stype;   //  数据类型：酒店，路线，景点
    private String stime;   //  信息时间
    private String sfeature;    //  特色
    private int sstate;    //  审核状态
}
