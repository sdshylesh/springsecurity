package com.dextrispoc.dextris.jwtservice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dextrispoc.dextris.dao.AdminDao;
import com.dextrispoc.dextris.entity.Admin;

@Service
public class UserServiceJwt implements UserDetailsService {
	@Autowired
	private AdminDao adminDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminDao.getAdminByName(username);
		if (admin == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return new User(admin.getAdminName(), admin.getAdminPwd(), new ArrayList<>());
	}

}
