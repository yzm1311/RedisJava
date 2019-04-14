package com.yzm.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class JedisSortedSet {

    public static  void main (String[] args) {
        Jedis jedis = new Jedis("localhost", 6380);

        jedis.auth("12345");
        jedis.flushAll();



        jedis.zadd("sset",100 ,"zion");
        jedis.zadd("sset",90 ,"peter");
        jedis.zadd("sset",90 ,"jim");
        jedis.zadd("sset",80 ,"tom");

        jedis.zscore("sset","jim");//Jim的成绩
        jedis.zincrby("sset",5,"jim");//给一个元素增加 5分

        Set<String> sset = jedis.zrangeByScore("sset", 60, 100);//打印分数区间。
      for (String ss:sset){
          System.out.print(ss);
      }

    }


}
