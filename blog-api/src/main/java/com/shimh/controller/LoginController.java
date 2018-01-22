package com.shimh.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.shimh.common.constant.Base;
import com.shimh.common.constant.ResultCode;
import com.shimh.common.util.Result;
import com.shimh.entity.User;
import com.shimh.service.UserService;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public Result login(User user) {  
		
	    Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());
	    
	    Result r = new Result();
	    
	    try {  
	        subject.login(token);  

	        User currentUser = userService.getUserByAccount(user.getAccount());
	        subject.getSession().setAttribute(Base.CURRENT_USER, currentUser);
	        
	        r.setResultCode(ResultCode.SUCCESS);
	        r.simple().put("token", subject.getSession().getId());
	    } catch (UnknownAccountException e) {  
	    	r.setResultCode(ResultCode.USER_NOT_EXIST);
	    } catch (LockedAccountException e) {  
	    	r.setResultCode(ResultCode.USER_ACCOUNT_FORBIDDEN);
	    } catch (AuthenticationException e) {  
	    	r.setResultCode(ResultCode.USER_LOGIN_ERROR);
	    } catch (Exception e) {  
	    	r.setResultCode(ResultCode.ERROR);
	    }  
	    
	    return r;  
	} 
	
	
    @RequestMapping(value = "/handleLogin")  
    public Result handleLogin() {  
        return Result.error(ResultCode.USER_NOT_LOGGED_IN);
    }
}
