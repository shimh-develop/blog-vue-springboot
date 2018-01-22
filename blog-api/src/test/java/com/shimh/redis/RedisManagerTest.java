package com.shimh.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shimh.BlogApiApplicationTests;
import com.shimh.common.cache.RedisManager;

public class RedisManagerTest extends BlogApiApplicationTests{

	@Autowired
	private RedisManager redisManager;
	
	
	@Test
	public void setTest() {
		String k = "zzz";
		String v = "123789";
		redisManager.set(k, v);
	}
	
	@Test
	public void getTest() {
		String k = "zzz";
		String v = redisManager.get(k,String.class);
		System.out.println(v);
	}
	
	@Test
	public void deleteTest() {
		String k = "zzz";
		redisManager.delete(k);
		String v = redisManager.get(k,String.class);
		System.out.println(v);
	}
	
}
