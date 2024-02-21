package com.dextrispoc.dextris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dextrispoc.dextris.dao.AdminDao;
import com.dextrispoc.dextris.dao.UserDao;
import com.dextrispoc.dextris.entity.Admin;
import com.dextrispoc.dextris.entity.User;

import jakarta.transaction.Transactional;

@Service
public class AdminService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private AdminDao adminDao;

	public String creatingAdmin(Admin admin) {
		System.out.println("hello service");
		adminDao.save(admin);
		return "saved";

	}

	public String loginVerification(String name, String pwd) {
		Admin admin = adminDao.getAdminByName(name);
		String userName = admin.getAdminName();
		String UserPwd = admin.getAdminPwd();
		if (userName.equals(name) && UserPwd.equals(pwd)) {
			return "verified";
		}
		return "Plz check credentials";
	}

	public User gettingUserbyName(String name) {
		User user = userDao.getUserByName(name);
		return user;

	}

	public List<User> gettingAllUser() {
		List<User> list = userDao.findAll();
		return list;

	}
	 @Transactional
	public String deletingUserbyName(String name) {
		int outcome = userDao.deleteUserByName(name);
		if (outcome > 0) {
			return "deleted";
		} else {
			return "No user found with the specified name";
		}

	}

	public String updateUserbyName(User user) {
		   // Retrieve the existing user from the database based on the username
	    User userFound = userDao.getUserByName(user.getUserName());

	    // Check if the user exists
	    if (userFound != null) {
	        userFound.setDob(user.getDob());
	        userFound.setPhoneNum(user.getPhoneNum());
	        userFound.setRole(user.getRole());
	        userFound.setUserAge(user.getUserAge());
	        userFound.setUserName(user.getUserName());
	        userDao.save(userFound);      
	        return "User updated successfully";
	    } else {
	        // Return an error message if the user is not found
	        return "User not found";
	    }

	}

}
