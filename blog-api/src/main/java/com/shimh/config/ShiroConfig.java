package com.shimh.config;


import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.shimh.common.cache.RedisManager;
import com.shimh.oauth.OAuthRealm;
import com.shimh.oauth.OAuthSessionDAO;
import com.shimh.oauth.OAuthSessionManager;

@Configuration  
public class ShiroConfig {  
  
    @Bean  
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {  
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();  
        shiroFilterFactoryBean.setSecurityManager(securityManager);  
  
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();  
        //注意过滤器配置顺序 不能颠倒  
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl  
        filterChainDefinitionMap.put("/logout", "logout");  
        // 配置不会被拦截的链接 顺序判断  
        filterChainDefinitionMap.put("/static/**", "anon");  
        filterChainDefinitionMap.put("/ajaxLogin", "anon");  
        filterChainDefinitionMap.put("/login", "anon");  
        filterChainDefinitionMap.put("/**", "authc");  
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据  
        
        shiroFilterFactoryBean.setLoginUrl("/unauth");  
        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);  
        return shiroFilterFactoryBean;  
    }  
  
    @Bean  
    public HashedCredentialsMatcher hashedCredentialsMatcher() {  
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();  
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;  
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));  
        return hashedCredentialsMatcher;  
    }  
  
    @Bean  
    public OAuthRealm oAuthRealm() {  
    	OAuthRealm myShiroRealm = new OAuthRealm();  
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());  
        return myShiroRealm;  
    }  
  
  
    @Bean  
    public SecurityManager securityManager(OAuthRealm oAuthRealm, SessionManager sessionManager) {  
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();  
        securityManager.setRealm(oAuthRealm);  
        securityManager.setSessionManager(sessionManager);  
        // 自定义缓存实现 使用redis  
        //securityManager.setCacheManager(cacheManager());  
        return securityManager;  
    }  
  
    @Bean  
    public SessionManager sessionManager(OAuthSessionDAO authSessionDAO) {  
    	OAuthSessionManager mySessionManager = new OAuthSessionManager();  
        mySessionManager.setSessionDAO(authSessionDAO);  
        return mySessionManager;  
    }  
  
  
    @Bean
    public OAuthSessionDAO authSessionDAO(RedisManager redisManager) {  
    	OAuthSessionDAO authSessionDAO = new OAuthSessionDAO();  
    	authSessionDAO.setRedisManager(redisManager);
        return authSessionDAO;  
    }  
  

    @Bean  
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {  
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();  
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);  
        return authorizationAttributeSourceAdvisor;  
    } 

}
