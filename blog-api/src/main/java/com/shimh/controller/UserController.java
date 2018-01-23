package com.shimh.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shimh.common.constant.Base;
import com.shimh.common.constant.ResultCode;
import com.shimh.common.result.Result;
import com.shimh.entity.User;
import com.shimh.service.UserService;
/**
 * 用户api
 * 
 * @author shimh
 *
 * 2018年1月23日
 *
 */
@RestController
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public Result listUsers(){
		List<User> users = userService.findAll();
		
		return Result.success(users);
	}
	
	@GetMapping("/{id}")
	public Result getUserById(@PathVariable("id") Long id){
		
		Result r = new Result();
		
		if(null == id){
			r.setResultCode(ResultCode.PARAM_IS_BLANK);
			return r;
		}
		
		User user = userService.getUserById(id);
		
		r.setResultCode(ResultCode.SUCCESS);
		r.setData(user);
		return r;
	}
	
	
	@PostMapping("/create")
	@RequiresRoles(Base.ROLE_ADMIN)
	public Result saveUser( @Validated @RequestBody User user){
		
		Long userId = userService.saveUser(user);
		
		Result r = Result.success();
		r.simple().put("userId", userId);
		return r;
	}
	
	@PostMapping("/update")
	@RequiresRoles(Base.ROLE_ADMIN)
	public Result updateUser(@RequestBody User user){
		Result r = new Result();
		
		if(null == user.getId()){
			r.setResultCode(ResultCode.USER_NOT_EXIST);
			return r;
		}
		
		Long userId = userService.updateUser(user);
		
		r.setResultCode(ResultCode.SUCCESS);
		r.simple().put("userId", userId);
		return r;
	}
	
	@GetMapping("/delete/{id}")
	@RequiresRoles(Base.ROLE_ADMIN)
	public Result deleteUserById(@PathVariable("id")Long id){
		Result r = new Result();
		
		if(null == id){
			r.setResultCode(ResultCode.PARAM_IS_BLANK);
			return r;
		}
		
		userService.deleteUserById(id);
		
		r.setResultCode(ResultCode.SUCCESS);
		return r;
	}
	
}
