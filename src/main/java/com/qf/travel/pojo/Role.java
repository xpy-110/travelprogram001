package com.qf.travel.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Role implements Serializable {
    private int rid;//int(11) NOT NULL
    private String rname;//varchar(20) NULL
}
