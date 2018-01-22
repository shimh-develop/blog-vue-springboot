package com.shimh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shimh.entity.User;

public interface UserRepository extends JpaRepository <User, Long>{

	User findByAccount(String account);

}
