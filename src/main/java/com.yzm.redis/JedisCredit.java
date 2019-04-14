package com.yzm.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

public class JedisCredit {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost",6380);
        jedis.auth("12345");
        jedis.flushDB();

        jedis.set("credit","4000");
        jedis.set("debt","1000");
        int out = 1000;
        int credit = Integer.parseInt(jedis.get("credit"));
        System.out.println(credit+" "+" 信用卡余额");

        if(credit<out){
            System.out.println("取款失败");
        }else{

            jedis.watch("credit","debt");
            jedis.set("credit","8000");//模拟被监视数据被事物以外的数据修改
            Transaction t = jedis.multi();//获取事物对象
            t.decrBy("credit",out);//信用余额
            t.incrBy("debt",out);//债务余额

            List<Object> exec = t.exec();
            jedis.unwatch();//解除监控
            System.out.println(exec);
            if(!exec.isEmpty()){
                System.out.println("取款成功");
                System.out.println("debt"+jedis.get("debt")+" :"+ "credit"+jedis.get("credit"));
            }else{
                System.out.println("取款失败");
            }
            System.out.println("debt"+jedis.get("debt")+" :"+ "credit"+jedis.get("credit"));
        }



    }
}
