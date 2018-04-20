package com.shimh.common.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * RedisManager
 *
 * @author shimh
 * <p>
 * 2018年1月23日
 */
public class RedisManager {

    /**
     * 默认过期时长，单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 30 * 1;
    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;

    private RedisTemplate redisTemplate;


    public void set(String key, Object value, long expire) {
        try {
            if (expire == NOT_EXPIRE) {
                redisTemplate.opsForValue().set(key, value);
            } else {
                redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void set(String key, Object value) {
        set(key, value, DEFAULT_EXPIRE);
    }

    public <T> T get(String key, Class<T> clazz) {
        ValueOperations<String, T> operations = redisTemplate.opsForValue();
        return operations.get(key);

    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

}
