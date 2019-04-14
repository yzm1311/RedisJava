package com.yzm.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class JedisSet {

    public static  void main (String[] args) {
        Jedis jedis = new Jedis("localhost", 6380);

        jedis.auth("12345");
        jedis.flushAll();

        jedis.sadd("set1","tom","jim","peter");//add
        //del
        jedis.srem("set1","jim");

         jedis.sadd("set2","smith","rose","tom");

        Set<String> sinter = jedis.sinter("set1", "set2");
        for(String  s1 :sinter){
            System.out.print(s1);
        }
       jedis.sinterstore("set3","set2","set1");
        Set<String> set1 = jedis.smembers("set1");
         for (String s:set1){
             System.out.print(s);
         }

    }
}
