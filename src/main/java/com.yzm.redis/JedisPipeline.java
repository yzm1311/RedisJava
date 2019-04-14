package com.yzm.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class JedisPipeline {
    public static void main (String [] args){
      //连接数据库
        Jedis  jedis = new Jedis("localhost",6380);

        jedis.auth("12345");
        jedis.flushAll();

        long  start = System.currentTimeMillis();//开始时间
        Pipeline pl = jedis.pipelined();
        int  count =10000;
        // jedis.set("key"+i,"100");
        for(int i=0;i<count;i++)
        {
            pl.set(i + "key", "tps");//使用Pipleline 插入
        }
        pl.sync();//管道同步
        long  end =System.currentTimeMillis();//结束时间


        System.out.print(end-start+"MS");
        jedis.close();
    }

}
