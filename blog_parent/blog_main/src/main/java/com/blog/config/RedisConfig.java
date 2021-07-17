package com.blog.config;

import com.blog.common.cache.RedisManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {


    @Bean
    public RedisManager redisManager(RedisTemplate redisTemplate) {
        RedisManager redisManager = new RedisManager();
        redisManager.setRedisTemplate(redisTemplate);
        return redisManager;
    }

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);

        StringRedisSerializer ss = new StringRedisSerializer();
        //ExtGenericFastJsonRedisSerializer extGenericFastJsonRedisSerializer = new ExtGenericFastJsonRedisSerializer();

        redisTemplate.setKeySerializer(ss);
        // redisTemplate.setValueSerializer(extGenericFastJsonRedisSerializer);

        return redisTemplate;
    }
}  
