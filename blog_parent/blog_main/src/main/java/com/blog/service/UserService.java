package com.blog.service;

import com.blog.entity.User;

import java.util.List;

/**
 * @author blog
 * <p>
 * 2018年1月23日
 */
public interface UserService {

    List<User> findAll();

    User getUserByAccount(String account);

    User getUserById(Long id);

    Long saveUser(User user);

    Long register(User user,String code);

    Long updateUser(User user);

    void deleteUserById(Long id);

    void  sendsms(String mobilephonenumber);
}
