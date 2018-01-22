package com.shimh.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shimh.common.cache.RedisManager;
import com.shimh.common.json.ExtGenericFastJsonRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration  
public class RedisConfig {  
  
      
    @Bean
    public RedisManager redisManager(RedisTemplate redisTemplate){
    	RedisManager redisManager = new RedisManager();
    	redisManager.setRedisTemplate(redisTemplate);
    	return redisManager;
    }
    
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory){
    	
    	RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        
        StringRedisSerializer ss = new StringRedisSerializer();
        //ExtGenericFastJsonRedisSerializer extGenericFastJsonRedisSerializer = new ExtGenericFastJsonRedisSerializer();
        
        redisTemplate.setKeySerializer(ss);
       // redisTemplate.setValueSerializer(extGenericFastJsonRedisSerializer);
        
        return redisTemplate;
    }
}  
