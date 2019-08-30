package com.qf.travel.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class NumEcharts implements Serializable {
    private int value;
    private String name;

    public NumEcharts() {
    }

    public NumEcharts(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
