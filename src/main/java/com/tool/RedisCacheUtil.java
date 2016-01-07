package com.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.SafeEncoder;

/**
 * Created by Administrator on 2016/1/6.
 */
@Component
public class RedisCacheUtil {
    @Autowired
    ShardedJedisPool shardedJedisPool;

    public ShardedJedis getCacheClient() {
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        return shardedJedis;
    }

    public SerializerUtil getSerilizer() {
        SerializerUtil serializerUtil = new SerializerUtil();
        return serializerUtil;
    }

    public <T> boolean set(String key, T value) {
        String result = "";
        try {
            result = getCacheClient().set(SafeEncoder.encode(key), getSerilizer().serialize(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "OK".equals(result) ? true : false;
    }
    public <T> boolean setEx(String key,int second,T value)  {
        String result="";
        try {
            result = getCacheClient().setex(SafeEncoder.encode(key),second,getSerilizer().serialize(value));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "OK".equals(result)?true:false;
    }

    public <T> T get(String key, Class<T> t) {
        byte[] bytes = getCacheClient().get(SafeEncoder.encode(key));
        if (bytes == null) {
            return null;
        }
        return (T) getSerilizer().deserialize(bytes);
    }

}
