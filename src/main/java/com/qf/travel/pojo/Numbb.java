package com.qf.travel.pojo;

import lombok.Data;

import java.io.Serializable;
@Data
public class Numbb implements Serializable {
    private String name;
    private int value;

    public Numbb() {
    }

    public Numbb(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
