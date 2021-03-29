package com.jump.datareciever.controller;

import com.jump.datareciever.dao.AccelDataDao;
import com.jump.datareciever.entity.AccelData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author: chuanyi@88.com
 * @description:
 * @date: 2021/3/16 23:22
 */
@RestController
public class UploadController {

    private final Map<String, List<List<Double>>> xDataMap = new HashMap<>();
    private final Map<String, List<List<Double>>> yDataMap = new HashMap<>();
    private final Map<String, List<List<Double>>> zDataMap = new HashMap<>();

    @Autowired
    private AccelDataDao accelDataDao;

    private int batch = 0;

    /**
     * curl -H "content-type:application/json"  localhost:6666/upload -XPOST -d '{"x":[1,2],"y":[1,2],"z":[1,24],"p":"jksjkd"}'
     *
     * @param data
     * @return
     */
    @PostMapping("/upload")
    public int uploadData(@RequestBody AccelData data) {
        ++batch;
        String profileKey = data.getP();
        System.out.printf("------Batch %d Data receiving------%n", batch);
        xDataMap.computeIfPresent(data.getP(), (k, v) -> {
            v.add(data.getX());
            return v;
        });
        putIfAbsent(xDataMap, data.getX(), profileKey);

        yDataMap.computeIfPresent(profileKey, (k, v) -> {
            v.add(data.getY());
            return v;
        });
        putIfAbsent(yDataMap, data.getY(), profileKey);

        zDataMap.computeIfPresent(profileKey, (k, v) -> {
            v.add(data.getZ());
            return v;
        });
        putIfAbsent(zDataMap, data.getZ(), profileKey);

        System.out.printf("------Batch %d Data received------%n", batch);
        return 0;
    }

    private void putIfAbsent(Map<String, List<List<Double>>> dataMap, List<Double> axis, String profileKey) {
        if (!dataMap.containsKey(profileKey)) {
            List<List<Double>> lists = new ArrayList<>();
            lists.add(axis);
            dataMap.put(profileKey, lists);
        }
    }

    @GetMapping("/hello")
    public String hello() {
        return "Do you jump today?";
    }

    @PostMapping("/start")
    public String start(@RequestBody AccelData data) {
        System.out.printf("%s's data upload starts", data.getP());
        System.out.println();
        return "start";
    }

    @PostMapping("/reset")
    public String reset(@RequestBody AccelData data) {
        String profileKey = data.getP();
        if (!xDataMap.containsKey(profileKey)) {
            return "not exist";
        }
        String uuid = UUID.randomUUID().toString().replace("-","");
        System.out.printf("%s's data upload ends", uuid + profileKey);
        System.out.println();
        String date = new Date(System.currentTimeMillis()).toString();
        System.out.println(" -----------------" + "data saving start At " + date + "-----------------");
        accelDataDao.saveAccelDataCollection(data.getCount(),uuid,profileKey,xDataMap,yDataMap,zDataMap);
        System.out.println("-----------------" + "data saved At " + date + "-----------------");
        xDataMap.remove(profileKey);
        yDataMap.remove(profileKey);
        zDataMap.remove(profileKey);
        return "reset";
    }
}
