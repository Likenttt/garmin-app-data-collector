package com.jump.datareciever.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: chuanyi@88.com
 * @description:
 * @date: 2021/3/16 23:24
 */
@Data
public class AccelData implements Serializable {
    private static final long serialVersionUID = -9085117698041233252L;
    private List<Double> x;
    private List<Double> y;
    private List<Double> z;
    /**
     * profile
     */
    private String p;
    private int count;
}
