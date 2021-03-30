package com.jump.datareciever.dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jump.datareciever.entity.DataCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author: chuanyi@88.com
 * @description:
 * @date: 2021/3/27 18:57
 */
@Service
public class AccelDataDao {
    private final String ACCEL_KEY_PREFIX = "connectiq;accel:axes:";
    private final String COUNT_KEY_PREFIX = "connectiq;count:";
    private final Gson gson = new Gson();
    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    public void saveAccelDataCollection(int count, String uuid, String profileKey, Map<String, List<List<Double>>> xDataMap, Map<String, List<List<Double>>> yDataMap, Map<String, List<List<Double>>> zDataMap) {
        DataCollection dataCollection = new DataCollection();
        List<List<Double>> xLists = xDataMap.get(profileKey);
        List<List<Double>> yLists = yDataMap.get(profileKey);
        List<List<Double>> zLists = zDataMap.get(profileKey);
        dataCollection.setBatchCount(xLists.size());
        dataCollection.setX(xLists);
        dataCollection.setY(yLists);
        dataCollection.setZ(zLists);
        dataCollection.setMark(uuid + profileKey);
        dataCollection.setCount(count);
        String accelRedisKey = ACCEL_KEY_PREFIX + uuid + ":" + profileKey;
        String countRedisKey = COUNT_KEY_PREFIX + uuid + ":" + profileKey+"_"+count;
        System.out.println("saving value for redis key:    " + accelRedisKey);
        System.out.println("saving value for count key:    " + countRedisKey);
        stringRedisTemplate.opsForValue().set(accelRedisKey, gson.toJson(dataCollection));
        stringRedisTemplate.opsForValue().set(countRedisKey, String.valueOf(count));
    }

    public DataCollection get(String key) {
        String s = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(s)){
            return null;
        }
        return gson.fromJson(s, DataCollection.class);
    }


}
