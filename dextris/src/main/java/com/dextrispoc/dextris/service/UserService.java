package com.dextrispoc.dextris.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dextrispoc.dextris.dao.UserDao;
import com.dextrispoc.dextris.entity.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public String creatingUser(User user) {

		userDao.save(user);
		return "saved";

	}
	

}
