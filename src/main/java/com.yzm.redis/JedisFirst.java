package com.yzm.redis;

import redis.clients.jedis.Jedis;
import java.util.Set;

public class JedisFirst {
     public static  void  main(String[] args ){
         //连接redis 数据库实例
         Jedis jedis =new Jedis("localhost",6380);
         jedis.auth("12345");
         System.out.print(jedis.getDB());
         jedis.set("a","10");
         System.out.print(jedis.getDB());

         jedis.flushDB();
         jedis.flushAll();

         jedis.set("a","jim");
         jedis.set("b","bob");
         jedis.set("c","Rose");

         Set<String> keys = jedis.keys("*");
         for(String  k:keys){
             System.out.print(k+": "+ jedis.get(k));
         }
         System.out.println(jedis.dbSize());
     }


}
