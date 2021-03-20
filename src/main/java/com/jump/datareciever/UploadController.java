package com.jump.datareciever;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: chuanyi@88.com
 * @description:
 * @date: 2021/3/16 23:22
 */
@RestController
public class UploadController {

    private Map<String,List<List<Double>>> xDataMap = new HashMap<>();
    private Map<String,List<List<Double>>> yDataMap = new HashMap<>();
    private Map<String,List<List<Double>>> zDataMap = new HashMap<>();

    private int batch = 0;

    @PostMapping("/upload")
    public int uploadData(@RequestBody AccelData data){
        ++batch;
        System.out.printf("------Batch %d Data receiving------%n", batch);
        xDataMap.computeIfPresent(data.getP(), (k,v) ->{
            v.add(data.getX());
            return v;
        });
        xDataMap.putIfAbsent(data.getP(), Arrays.asList(data.getX()));

        yDataMap.computeIfPresent(data.getP(), (k,v) ->{
            v.add(data.getY());
            return v;
        });
        yDataMap.putIfAbsent(data.getP(), Arrays.asList(data.getY()));

        zDataMap.computeIfPresent(data.getP(), (k,v) ->{
            v.add(data.getZ());
            return v;
        });
        zDataMap.putIfAbsent(data.getP(), Arrays.asList(data.getZ()));

        System.out.printf("------Batch %d Data received------%n", batch);
        return 0;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Do you jump today?";
    }

    @PostMapping("/start")
    public String start(@RequestBody AccelData data){
        System.out.printf("%s's data upload starts",data.getP());
        return "start";
    }
    @PostMapping("/reset")
    public String reset(@RequestBody AccelData data){
        if (!xDataMap.containsKey(data.getP())){
            return "not exist";
        }
        System.out.printf("%s's data upload ends",data.getP());
        System.out.println(data.getP()+" x axis :"+xDataMap.get(data.getP()));
        System.out.println(data.getP()+" y axis :"+yDataMap.get(data.getP()));
        System.out.println(data.getP()+" z axis :"+zDataMap.get(data.getP()));
        return "reset";
    }
}
