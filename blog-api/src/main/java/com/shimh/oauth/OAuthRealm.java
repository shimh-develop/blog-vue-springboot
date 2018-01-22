package com.shimh.oauth;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.shimh.common.constant.Base;
import com.shimh.entity.User;
import com.shimh.entity.UserStatus;
import com.shimh.service.UserService;

public class OAuthRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String account = (String)principals.getPrimaryPrincipal();
        User user = userService.findByAccount(account);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<String>();
        
        if(user.getAdmin()){
        	roles.add(Base.ROLE_ADMIN);
        }
        
        authorizationInfo.setRoles(roles);
        //authorizationInfo.setStringPermissions(userService.findPermissions(username));

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String account = (String)token.getPrincipal();

        User user = userService.findByAccount(account);

        if(user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        if(UserStatus.blocked.equals(user.getStatus())) {
            throw new LockedAccountException(); //帐号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getAccount(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getSalt()),
                getName()  //realm name
        );
        
        return authenticationInfo;
    }
	
	
	
	
	
	
	
	
	

}
