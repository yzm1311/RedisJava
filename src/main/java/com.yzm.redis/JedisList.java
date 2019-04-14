package com.yzm.redis;

import redis.clients.jedis.Jedis;

import java.util.List;

public class JedisList {
    public static void main(String[] args) {
//双向列表
        //连接数据库实例
        Jedis jedis = new Jedis("localhost", 6380);
        jedis.auth("12345");
        jedis.flushAll();

        jedis.lpush("list_1", "aa", "bb", "cc");//  cc bb aa   从左边开始放

        jedis.rpush("list_1", "ee", "ff", "gg");//cc bb aa ee ff gg
        System.out.print(jedis.lpop("list_1"));
        System.out.print(jedis.llen("list_1"));  //查询长度

        System.out.print(jedis.lindex("list_1", 2));//查询低级个位置的值
  //取得部分或者全部元素
        List<String> list_1 = jedis.lrange("list_1", 0, 5);
        for (String l : list_1) {
            System.out.print(l);
        }
    }
}
