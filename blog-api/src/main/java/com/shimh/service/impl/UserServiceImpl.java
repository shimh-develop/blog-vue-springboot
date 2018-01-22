package com.shimh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.shimh.common.util.PasswordHelper;
import com.shimh.entity.User;
import com.shimh.repository.UserRepository;
import com.shimh.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(User user) {
		
		PasswordHelper.encryptPassword(user);
		userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByAccount(String account) {
		return userRepository.findByAccount(account);
	}
	
}
