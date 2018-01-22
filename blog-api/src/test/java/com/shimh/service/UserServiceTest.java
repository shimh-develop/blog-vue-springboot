package com.shimh.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shimh.BlogApiApplicationTests;
import com.shimh.entity.User;

public class UserServiceTest extends BlogApiApplicationTests{

	@Autowired
	private UserService userService;
	
	@Test
	public void jpaTest() {
		User u = new User();
		
		u.setAccount("shimh");
		
		userService.save(u);
	}
	
	
	
	@Test
	public void cacheTest() {
		
		List<User> u1 = userService.findAll();
		
		System.out.println("------------");
		
		List<User> u2 = userService.findAll();
	}
	
}
