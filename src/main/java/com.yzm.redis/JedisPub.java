package com.yzm.redis;

import redis.clients.jedis.Jedis;
//消息的发布者
public class JedisPub {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost",6380);
        jedis.auth("12345");
        jedis.flushDB();
         //每隔一秒发布一条消息
        for(int i =0;i<10;i++){
            jedis.publish("cctv","cctv"+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
