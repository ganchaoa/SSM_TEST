package com.gan.ssm.service;

import java.util.List;

import com.gan.ssm.entity.User;

public interface UserService {

	public abstract User findByUsername(String username);
	
	public abstract List<User> findAll();
}
