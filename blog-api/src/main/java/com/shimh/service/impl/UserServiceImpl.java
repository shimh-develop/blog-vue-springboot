package com.shimh.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shimh.common.util.PasswordHelper;
import com.shimh.entity.User;
import com.shimh.repository.UserRepository;
import com.shimh.service.UserService;

/**
 * @author shimh
 * <p>
 * 2018年1月23日
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByAccount(String account) {
        return userRepository.findByAccount(account);
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findOne(id);
    }

    @Override
    @Transactional
    public Long saveUser(User user) {

        PasswordHelper.encryptPassword(user);
        int index = new Random().nextInt(6) + 1;
        String avatar = "/static/user/user_" + index + ".png";

        user.setAvatar(avatar);
        return userRepository.save(user).getId();
    }


    @Override
    @Transactional
    public Long updateUser(User user) {
        User oldUser = userRepository.findOne(user.getId());
        oldUser.setNickname(user.getNickname());

        return oldUser.getId();
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.delete(id);
    }

}
