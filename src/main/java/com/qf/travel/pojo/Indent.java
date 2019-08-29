package com.qf.travel.pojo;

import lombok.Data;

@Data
public class Indent {
    private int id;//订单id
    private int sid;//景点id
    private String uname;//订单提交用户
    private int icount;//订单数量
    private double iprice;//订单价格
    private String itime;//订单提交时间
    private String istate;//订单状态
    private Scenic scenic = new Scenic();
   /* private String Sname;//景点名称
    private String Simgs;//景点图片
    private String Sprice;//景点价格*/
}
