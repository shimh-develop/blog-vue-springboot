package com.shimh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shimh.common.util.Result;
import com.shimh.entity.User;
import com.shimh.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public Result listUsers(){
		List<User> users = userService.findAll();
		
		return Result.success(users);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Result saveUser(User user){
		
		Long userId = userService.saveUser(user);
		
		return Result.success().simple().put("userId", userId);
	}
	
	
	
	
	
	
	
}
