package com.yzm.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class JedisSub {
    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost",6380);
        jedis.auth("12345");
      myListener myListener = new myListener(); //订阅频道

        jedis.subscribe(myListener,"cctv");
    }
//内部类，消息处理器
    private static  class myListener extends JedisPubSub{

        @Override
        public void onMessage(String channel, String message) {
            //super.onMessage(channel, message);
            System.out.println(channel+":"+ message);
        }
    }

}
