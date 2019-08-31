package com.qf.travel.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Numbb implements Serializable {
    private String name;
    private List<NumEcharts> list = new ArrayList<>();

    public Numbb() {
    }

    public Numbb(String name, List<NumEcharts> list) {
        this.name = name;
        this.list = list;
    }
}
