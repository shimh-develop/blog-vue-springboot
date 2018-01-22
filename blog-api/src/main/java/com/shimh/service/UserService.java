package com.shimh.service;

import java.util.List;

import com.shimh.entity.User;

public interface UserService {
	
	List<User> findAll();
	
	void save(User user);

	User findByAccount(String account);
}
