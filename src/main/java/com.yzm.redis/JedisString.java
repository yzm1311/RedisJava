package com.yzm.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class JedisString {
    public static  void main (String[] args){
        Jedis jedis = new Jedis("localhost", 6380);

        jedis.auth("12345");

        jedis.select(10);
        jedis.flushDB();

        jedis.set("county","china");  //增加一条数据
        System.out.println(jedis.exists("county"));
        System.out.println(jedis.strlen("county"));//获取value的长度

        jedis.append("county","  is great");//在字符串上加一部分字符串

        System.out.println(jedis.get("county"));

        jedis.setrange("county",9,"amazeing");//修改value 的一部分
        System.out.println(jedis.get("county"));

        System.out.println( jedis.getrange("county",9,15));//取得value中的一部分


        jedis.set("age","100");
        jedis.incr("1");//每次加多少
        jedis.incrBy("age",10);//，每次减多少
        jedis.decr("1");
        jedis.decrBy("age",10);

        Set<String> age = jedis.zrangeByScore("age", 0, 3);
        for(String a :age){
            System.out.print(a+" ");
        }

        jedis.flushDB();
        jedis.mset("name","zhangsan","age","10","county","china");//一次增加多条数据



    }
}
