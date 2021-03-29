package com.jump.datareciever.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: chuanyi@88.com
 * @description:
 * @date: 2021/3/27 18:41
 */
@Data
public class DataCollection implements Serializable {
    private static final long serialVersionUID = -4848531928419020951L;
    private String mark;
    private int batchCount;
    private int count;
    private List<List<Double>> x = new ArrayList<>();
    private List<List<Double>> y = new ArrayList<>();
    private List<List<Double>> z = new ArrayList<>();
}
