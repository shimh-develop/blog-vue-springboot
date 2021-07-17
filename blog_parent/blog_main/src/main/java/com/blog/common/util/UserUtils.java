package com.blog.common.util;

import com.blog.common.constant.Base;
import com.blog.entity.User;
import org.apache.shiro.SecurityUtils;

/**
 * @author blog
 * <p>
 * 2018年1月25日
 */
public class UserUtils {
//获取当前用户
    public static User getCurrentUser() {
        /*Apache Shiro是一个强大且易用的Java安全框架,执行身份验证、授权、密码和会话管理。
        使用Shiro的易于理解的API,您可以快速、轻松地获得任何应用程序,从最小的移动应用程序到最大的网络和企业应用程序。
         */
//        SecurityUtils是Shiro中得一个抽象类,其中getSubject用来获取Subject
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute(Base.CURRENT_USER);
        return user;
    }
}
