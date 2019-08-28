package com.qf.travel.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Permission implements Serializable {
    private int pid;//int(11) NOT NULL
    private String pname;//varchar(20) NULL
    private String mname;//varchar(20) NULL
    private int mcode;//varchar(20) NULL
    private String check;
}
