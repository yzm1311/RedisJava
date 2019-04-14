package com.yzm.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;

/**
 *  ctrl +alt +t 自生成 try -catch
 * Redis 事物处理实例
 */
public class JedisTransaction {
     public static void main(String[] args){

         Jedis jedis = new Jedis("localhost",6380);
         jedis.auth("12345");
         jedis.flushDB();//清空当前


//         try {
//             Transaction t = jedis.multi();//获取事物对象
//             t.set("a","tom");
//             int cc;//这句话会出错。模拟两个对象出错
//             cc = 100/0;
//             t.set("b","peter");
//             t.exec();//提交事物
//         } catch (Exception e) {
//             e.printStackTrace();
//         }

          jedis.set("money", "100");
          jedis.watch("money");//从开始监视
          jedis.set("money","80");  //开始监视后到事物提交前，如果被监视的数据被本事物以外的对象修改，事物将不会被提交。返回空列表
          Transaction t = jedis.multi();//获取事物对象
          t.set("money","120");
          List<Object> exec = t.exec();//提交事物
          jedis.unwatch();//解除监控
          System.out.print(exec);
          System.out.print("money="+jedis.get("money"));


     }

}
