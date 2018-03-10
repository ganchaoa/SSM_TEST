package com.gan.ssm.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gan.ssm.dao.UserDao;
import com.gan.ssm.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class TestAnno {

	@Autowired
	UserDao userDao;

	@Test
	public void test() {
		List<User> userList = userDao.findAll();
		for (User user : userList) {
			System.out.println(user.getUsername());
		}
	}
}
