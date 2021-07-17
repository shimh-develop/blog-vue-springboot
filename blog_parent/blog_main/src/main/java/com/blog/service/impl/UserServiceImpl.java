package com.blog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.common.util.PasswordHelper;
import com.blog.entity.User;
import com.blog.repository.UserRepository;
import com.blog.service.UserService;

/**
 * @author blog
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


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate;

    /**
     * 发送手机验证码
     * @param mobilephonenumber
     */
    @Override
    @Transactional
    public void sendsms(String mobilephonenumber) {

        //1.随机生成6位数字字符串
        String code = RandomStringUtils.randomNumeric(6);

        //设置过期时间(5分钟后过期)
        redisTemplate.opsForValue().set("sms_"+mobilephonenumber,code,5, TimeUnit.MINUTES);

        //3.让RabbitMQ发送消息
        Map<String,String> map = new HashMap<String,String>();
        map.put("mobile",mobilephonenumber);
        map.put("code",code);

        rabbitMessagingTemplate.convertAndSend("sms",map);
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
    public Long register(User user,String code){
        //1.从redis取出当初发给这个用户的验证码
        String redisCode = (String) redisTemplate.opsForValue().get("sms_" +user.getMobilePhoneNumber());
        System.out.println(code);
        if(redisCode==null){
            throw new RuntimeException("请重新发送验证码");
        }

        if(!redisCode.equals(code)){
            throw new RuntimeException("验证码输入有误 ");
        }
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
