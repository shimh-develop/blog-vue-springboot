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

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shimh.common.cache.RedisManager;

import redis.clients.jedis.JedisPoolConfig;

@Configuration  
public class RedisConfig {  
  
    private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);  
      
   /* @Bean  
    @ConfigurationProperties(prefix="spring.redis")  
    public JedisPoolConfig jedisPoolConfig(){  
        JedisPoolConfig config = new JedisPoolConfig();  
        logger.info("JedisPoolConfig bean init success.");
        return config;  
    }  
      
    @Bean  
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig){  
        JedisConnectionFactory factory = new JedisConnectionFactory();  
        factory.setPoolConfig(jedisPoolConfig); 
        logger.info("JedisConnectionFactory bean init success.");
        return factory;  
    }  
      
      
    @Bean  
    @Qualifier("meRedisTemplate")
    public RedisTemplate<String,Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory){  
        
    	RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String,Object>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);

        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);//设置默认的Serialize，包含 keySerializer & valueSerializer

        //redisTemplate.setKeySerializer(fastJsonRedisSerializer);//单独设置keySerializer
        //redisTemplate.setValueSerializer(fastJsonRedisSerializer);//单独设置valueSerializer
        
        logger.info("RedisTemplate bean init success.");  
        return redisTemplate;
    }  
    
    @Bean
    @Qualifier("meRedisTemplate")
    public RedisManager redisManager(RedisTemplate<String,Object> redisTemplate){
    	RedisManager redisManager = new RedisManager();
    	redisManager.setRedisTemplate(redisTemplate);
    	return redisManager;
    }*/
    @Bean
    @Qualifier("meRedisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
    	RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String,Object>();
        redisTemplate.setConnectionFactory(factory);

        GenericFastJsonRedisSerializer fastJsonRedisSerializer = new GenericFastJsonRedisSerializer();
        redisTemplate.setDefaultSerializer(fastJsonRedisSerializer);//设置默认的Serialize，包含 keySerializer & valueSerializer

        //redisTemplate.setKeySerializer(fastJsonRedisSerializer);//单独设置keySerializer
        //redisTemplate.setValueSerializer(fastJsonRedisSerializer);//单独设置valueSerializer
        
        logger.info("RedisTemplate bean init success.");  
        return redisTemplate;
    }
    
    @Bean
    public RedisManager redisManager( @Qualifier("meRedisTemplate")RedisTemplate<String,Object> redisTemplate){
    	RedisManager redisManager = new RedisManager();
    	redisManager.setRedisTemplate(redisTemplate);
    	return redisManager;
    }
    
}  
