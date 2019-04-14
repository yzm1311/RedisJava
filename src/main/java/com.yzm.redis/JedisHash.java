package com.yzm.redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisHash {

      public  static  void  main (String [] args){


          Jedis jedis = new Jedis("localhost",6380);
          jedis.auth("12345");
          HashMap<String ,String> hashMap  =new HashMap<String, String>();
          hashMap.put("name","HP-3321");
          hashMap.put("Price","5000");
          jedis.hmset("hpcomputer",hashMap);//向redis中存入一个hash类型的数据
          System.out.println(jedis.hlen("hpcomputer"));//得到hash中的个数（元素）

          jedis.hset("hpcomputer","madin","china");//向redis中存入一个hash类型的数据  /修改，元素名字相同就OK
          System.out.println(jedis.hlen("hpcomputer"));//得到hash中的个数（元素）

          Map <String, String> map =jedis.hgetAll("hpcomputer");//得到一个hash类型的数据
          for( String  k :map.keySet()){
              System.out.println(k+":" +map.get(k));
          }

          System.out.println(jedis.hget("hpcomputer","Price"));//得到一条数据

          Set<String> hpcomputer = jedis.hkeys("hpcomputer");

          for (String  h:hpcomputer){
                System.out.print(" "+h);
          }

          List<String> hpcomputer1 = jedis.hvals("hpcomputer");
          for(String  l : hpcomputer){
              System.out.print(" "+l);
          }
      }
}
