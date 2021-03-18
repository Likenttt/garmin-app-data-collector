package com.jump.datareciever;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: chuanyi@88.com
 * @description:
 * @date: 2021/3/16 23:22
 */
@RestController
public class UploadController {

    private List<List<Double>> xDataList = new ArrayList<>();
    private List<List<Double>> yDataList = new ArrayList<>();
    private List<List<Double>> zDataList = new ArrayList<>();
    private int batch = 0;

    @PostMapping("/upload")
    public int uploadData(@RequestBody AccelData data){
        ++batch;
        System.out.printf("------Batch %d Data receiving------%n", batch);
        xDataList.add(data.getX());
        yDataList.add(data.getY());
        zDataList.add(data.getZ());
        System.out.println("x axis :"+xDataList);
        System.out.println("y axis :"+yDataList);
        System.out.println("z axis :"+zDataList);
        System.out.printf("------Batch %d Data received------%n", batch);
        return 0;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Do you run today?";
    }
}
