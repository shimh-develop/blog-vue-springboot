package com.shimh.redis;

import org.crazycake.shiro.RedisManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import com.shimh.BlogApiApplicationTests;

public class RedisManagerTest extends BlogApiApplicationTests{

	@Autowired
	private RedisManager redisManager;
	
	//@Autowired
	//@Qualifier("meRedisTemplate")
	private  RedisTemplate  meRedisTemplate;
	
	@Test
	public void jpaTest() {
		String k = "zzz";
		String v = "123";
		redisManager.set(k.getBytes(), v.getBytes());
	}
	
	@Test
	public void meRedisTemplateTest() {
		meRedisTemplate.opsForValue().set("zzz", 123);;

			
	}
}
