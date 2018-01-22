package com.shimh.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.shimh.entity.User;

@RestController
public class LoginController {

	
	@RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)  
	public String login(User user) {  
	    JSONObject jsonObject = new JSONObject();  
	    Subject subject = SecurityUtils.getSubject();  
	    UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), user.getPassword());  
	    try {  
	        subject.login(token);  
	        jsonObject.put("token", subject.getSession().getId());  
	        jsonObject.put("msg", "登录成功");  
	    } catch (UnknownAccountException e) {  
	        jsonObject.put("msg", "账号不存在");  
	    } catch (LockedAccountException e) {  
	        jsonObject.put("msg", "账号户已被冻结");  
	    } catch (AuthenticationException e) {  
	        jsonObject.put("msg", "账号或密码错误");  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	    
	    return jsonObject.toString();  
	} 
	
    @RequestMapping(value = "/unauth")  
    public Object unauth() {  
    	JSONObject jsonObject = new JSONObject();   
    	jsonObject.put("code", "1000000");  
    	jsonObject.put("msg", "未登录");  
        return jsonObject;  
    }
}
