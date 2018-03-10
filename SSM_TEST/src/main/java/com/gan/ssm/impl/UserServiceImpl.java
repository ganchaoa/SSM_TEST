package com.gan.ssm.impl;

import java.util.List;

import javax.annotation.Resource;

import com.gan.ssm.dao.UserDao;
import com.gan.ssm.entity.User;
import com.gan.ssm.service.UserService;

public class UserServiceImpl implements UserService {
    
	@Resource
	UserDao userDao;
	
	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

}
