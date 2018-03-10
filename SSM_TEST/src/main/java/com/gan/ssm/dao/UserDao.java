package com.gan.ssm.dao;

import java.util.List;

import com.gan.ssm.entity.User;

public interface UserDao {

	public abstract User findByUsername(String username);
	
	public abstract List<User> findAll();
	
}
