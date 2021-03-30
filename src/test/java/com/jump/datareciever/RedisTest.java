package com.jump.datareciever;

import com.google.gson.Gson;
import com.jump.datareciever.dao.AccelDataDao;
import com.jump.datareciever.entity.DataCollection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
    private AccelDataDao accelDataDao;
    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    @Test
    public void testString() {
        strRedisTemplate.opsForValue().set("key", "xxx");
        System.out.println(strRedisTemplate.opsForValue().get("key"));
    }

    @Test
    public void testGet() {
        String keys = "connectiq;accel:axes:501ac7014f6b41ccbeca06414fa1bfcb:1991-1-50-0,connectiq;accel:axes:09bcd725c82c4eaa8eaf4aeddd4bd7ef:1976-1-50-null,connectiq;accel:axes:683c27517bd84acebe6d014f55d9ed10:1988-1-50-null,connectiq;accel:axes:7d3d22a8049947119c7cce29f8977dee:1996-1-0-null,connectiq;accel:axes:57c89250586244669e9d040174d40a26:1996-1-0-null,connectiq;accel:axes:273f115f343b417ca94345e102ede12f:1971-1-50-null,connectiq;accel:axes:ee6cd95e1e2e4cd5bffc6bc0b8170fd0:1995-1-60-0,connectiq;accel:axes:6b16080857ad44858b8994b998a1bfa1:1985-1-50-0,connectiq;accel:axes:730dca97f8a94f758671c3340db8f8be:1994-0-70-null,connectiq;accel:axes:5396415111864e88b23d695d2f528c46:1980-0-75-900,connectiq;accel:axes:8dee7bf5b8c64f3eacbf135eae14db16:1981-0-50-null,connectiq;accel:axes:7a15bf3d0be8461aa28c77eacb62dd86:1988-0-50-null,connectiq;accel:axes:fe7d67766b88467ea7b8beab54137dd3:1996-1-0-null,connectiq;accel:axes:7336d2cc2fa74e86badcf1cc4d32af0b:1984-0-0-0,connectiq;accel:axes:d587c3572c8a4056ab56f47adef89e60:1996-1-0-null,connectiq;accel:axes:56b1f5dcfe224f60805ec15ebf6f8da1:1996-1-0-null,connectiq;accel:axes:82836b5fe6be4f0ea1959c40f3c26baa:1988-0-50-null,connectiq;accel:axes:9054eda2b285466fa1cee8864862e6e5:1996-1-0-null,connectiq;accel:axes:53d028c8f19345bc9afef1824b94f432:1982-0-70-null,connectiq;accel:axes:9019b741d84248a0beb7aad3d775d0c3:1986-1-70-null,connectiq;accel:axes:e94da0242745426d86c6a071787cb8cf:1965-1-70-0,connectiq;accel:axes:d7df2eda4dec4a82a7dae21b6506e905:1996-1-0-null,connectiq;accel:axes:7a74d03e30eb403a8838c036751076f4:1976-1-50-null,connectiq;accel:axes:4676d1c2d47748568423336a5e681635:1992-1-70-null,connectiq;accel:axes:01560a1b77f2488081c4cd2cd240f249:1996-1-0-null,connectiq;accel:axes:c9a17e56645f4037b1c22c3cd4e45de4:1996-1-0-null,connectiq;accel:axes:01f826f08e174b02ab65afb12daed473:1970-1-50-0,connectiq;accel:axes:6dc91afca0a74ec1b482cc7485ad5bce:1982-1-0-0,connectiq;accel:axes:14be8e9ab8f043f1ad4faa968772c741:1985-0-70-null,connectiq;accel:axes:7fda79cf8bd146948e76ea663eaab2a4:1973-0-70-null,connectiq;accel:axes:cf64e3da9cf44cce8ca327f4032432da:1976-1-50-null,connectiq;accel:axes:e5c36f8752fc4c10b7a8b433cb611a73:1996-1-0-null,connectiq;accel:axes:8b2c6d81fcf7419b8f4e4f16c738b0fe:1973-0-70-null,connectiq;accel:axes:5cf715b83cf84367b3243f7582d48996:1984-0-0-null,connectiq;accel:axes:bea5197880624e17ae3c39b5fd041ea6:1996-1-0-null,connectiq;accel:axes:99095f57a9e04fd79235e3ea4b0be4c4:1976-1-50-663,connectiq;accel:axes:40ece221c7e345c6912b62c8926fd9eb:1996-1-0-null,connectiq;accel:axes:5fdb3d33731047489f7e63cd90eefd1f:1983-1-0-0,connectiq;accel:axes:908b1f708c8f4789ac30f48de00c257e:1992-1-70-null,connectiq;accel:axes:74dab887948d4a6ba0a523507431700e:1988-1-50-null,connectiq;accel:axes:5264bca0a8474f328a59d0c02b906240:1991-1-70-null,connectiq;accel:axes:4b1e4e777ff643f28cb8d1bb167e5dc4:1996-1-0-null,connectiq;accel:axes:c06180b671ef4c0b830694c440104246:1980-0-75-900,connectiq;accel:axes:14bc07827bf54094977e11dbc75d13b9:1970-1-50-0,connectiq;accel:axes:3c43700cf95b423a800f081a20ea3b8d:1996-1-0-null,connectiq;accel:axes:6d9286fc5e87482e81082ee06708c176:1979-1-0-null,connectiq;accel:axes:b7c8e03de8474674bfda9fdfd0326761:1996-1-0-null,connectiq;accel:axes:22d59e04ec00471589557e37741a38fd:1983-0-70-null,connectiq;accel:axes:8c7efdb91bc74c2c893e5db4ed13ff53:1998-1-50-0,connectiq;accel:axes:48cd3cc31f30491b8cceb7a0b3135ce8:1988-1-50-null,connectiq;accel:axes:afd662ae518449fba31b06cba2ef2c41:1996-1-0-null,connectiq;accel:axes:3066b63032e34cb48fa6969bda7c265e:1988-1-50-null,connectiq;accel:axes:884ae8ea7fce4b20b0389ba58181ffc1:1996-1-0-null,connectiq;accel:axes:93da0945535641f1ac8084c966ec6bd8:1996-1-0-null,connectiq;accel:axes:9e40ea0e1fe149df9c31e56b88dadc36:1996-1-0-null,connectiq;accel:axes:9b656f4f5aa24c00b0d625c6d07d86f0:1996-1-0-null,connectiq;accel:axes:d86409e300724b13bf6e4f8187d81b49:1996-1-0-null,connectiq;accel:axes:b456584e17214ad9812bc4a91d91c4e4:1973-0-70-null,connectiq;accel:axes:bc82e8765fd94b7bb0b429509120d714:1988-1-50-null,connectiq;accel:axes:8577a306d664476bada8a9f1756b7e7c:1965-1-70-0,connectiq;accel:axes:166dabb6a9a34c15abf76374874d4bc3:1996-1-0-null,connectiq;accel:axes:304e2999afd944fca121fee333f7b244:1996-1-0-null,connectiq;accel:axes:07c30424ec1c49c687f517d4306860d7:1984-0-0-0,connectiq;accel:axes:3d69c9635c1a471a8173fadce05f922f:1988-1-70-null,connectiq;accel:axes:7d341dc4676a4a5595706022765ac1c9:1994-0-70-null,connectiq;accel:axes:bfce79113a954e378a44fc3762182881:1996-1-0-null,connectiq;accel:axes:1db11705a95944759d75f4370e01318f:1992-1-70-null,connectiq;accel:axes:5d3a4db49c2d4c86af4fb982d830d072:1976-1-50-663,connectiq;accel:axes:5ed661086b6e43e3865e04a4915b8b32:1988-0-50-null,connectiq;accel:axes:6480d6f2b9bf4867b597d21707f71ff2:1973-1-50-null,connectiq;accel:axes:ff0587ec94f94e9c874ff6abe8e76a0e:1996-1-0-null,connectiq;accel:axes:12ee84f76f294c8e85b0f0375cb6df47:1996-1-0-null,connectiq;accel:axes:101575783f0c4c12a0bfe446061f71c2:1958-1-100-0,connectiq;accel:axes:d7f6a214584f4a35ba7562fd6eae62c2:1996-1-0-null,connectiq;accel:axes:b96ddf912260478482d67fb39c1b8642:1979-1-70-0,connectiq;accel:axes:881b64ba9cc74416ad7c9d7f0b43a3b8:1996-1-0-null,connectiq;accel:axes:de8c4cb596f7441bb1639163b1d361fa:1996-1-0-null,connectiq;accel:axes:7fe55742250b404b831e631ed87cccd9:1984-0-0-null,connectiq;accel:axes:bb6a957601844c389b1360035f4550b5:1998-1-50-0,connectiq;accel:axes:6fea0abdf3994f0482d25760b62ca243:1996-1-0-null,connectiq;accel:axes:e4bf068ff9d84d229abcd432b794ce4d:1996-1-0-null,connectiq;accel:axes:0805c5a20f8f47c083e0933b3e9d790c:1958-1-100-0,connectiq;accel:axes:9754a7dde2f349fdb9af0f7aa3a87135:1976-1-50-663";
        String[] split = keys.split(",");
        List<Integer> list = new ArrayList<>();
        for (String s : split) {
            String str = strRedisTemplate.opsForValue().get(s);
            DataCollection collection = new Gson().fromJson(str, DataCollection.class);
//            System.out.println(collection.getX());
//            System.out.println(collection.getY());
//            System.out.println(collection.getZ());
            System.out.println(s + "______" + collection.getBatchCount());
            list.add(collection.getBatchCount());
        }
        list.sort(Comparator.comparingInt(x -> x));


    }

//    @Test
//    public void testSerializable() {
//        AccelData accelData = new AccelData();
//        accelData.setP("nihao");
//        accelData.setX(Lists.newArrayList(12.0));
//        accelData.setY(Lists.newArrayList(12.0));
//        accelData.setZ(Lists.newArrayList(12.0));
//
//        serializableRedisTemplate.opsForValue().set("xxx:nihao", accelData);
//        AccelData accelData1 = (AccelData) serializableRedisTemplate.opsForValue().get("xxx:nihao");
//        System.out.println(accelData1);
//    }


}
