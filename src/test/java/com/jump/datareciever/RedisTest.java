package com.jump.datareciever;

import com.jump.datareciever.entity.AccelData;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

/**
 * @author: chuanyi@88.com
 * @description:
 * @date: 2021/3/27 17:55
 */
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate<String, String> strRedisTemplate;
    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    @Test
    public void testString() {
        strRedisTemplate.opsForValue().set("key", "xxx");
        System.out.println(strRedisTemplate.opsForValue().get("key"));
    }

    @Test
    public void testSerializable() {
        AccelData accelData = new AccelData();
        accelData.setP("nihao");
        accelData.setX(Lists.newArrayList(12.0));
        accelData.setY(Lists.newArrayList(12.0));
        accelData.setZ(Lists.newArrayList(12.0));

        serializableRedisTemplate.opsForValue().set("xxx:nihao", accelData);
        AccelData accelData1 = (AccelData) serializableRedisTemplate.opsForValue().get("xxx:nihao");
        System.out.println(accelData1);
    }



}
