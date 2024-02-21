package com.dextrispoc.dextris.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dextrispoc.dextris.JwtUtil.JwtutilDex;
import com.dextrispoc.dextris.entity.Admin;
import com.dextrispoc.dextris.entity.User;
import com.dextrispoc.dextris.jwtservice.UserServiceJwt;
import com.dextrispoc.dextris.service.AdminService;

@RestController
@RequestMapping("/creatingAdmin")
@CrossOrigin
public class AdminController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private JwtutilDex jwtutilDex;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/hello")
	public ResponseEntity<String> check() {
		return ResponseEntity.status(200).body("hello  i am there");
	}

	
	@PostMapping("/admin")
	public ResponseEntity<String> creatingAdmin(@RequestBody Admin admin) {
		System.out.println(admin.toString());
//		  String rawPassword = "password123";
//	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//	        String encodedPassword = encoder.encode(rawPassword);
		String rawPassword=admin.getAdminPwd();
		 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		 String encodedPwd=encoder.encode(rawPassword);
		 admin.setAdminPwd(encodedPwd);
		String gettingRespose = adminService.creatingAdmin(admin);
		if (gettingRespose.equals("saved")) {
			return ResponseEntity.status(200).body(gettingRespose);
		}
		return ResponseEntity.status(400).body(null);
	}
	@PostMapping("/verify")
	public String loginVerification(@RequestBody Map<String, String> credentials) throws Exception {
		String username = credentials.get("username");
		String password = credentials.get("password");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password));
		if (authentication.isAuthenticated()) {
		
			return jwtutilDex.generateToken(username);
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
//		String gettingRespose = adminService.loginVerification(username, password);
//		if (gettingRespose.equals("verified")) {
//			return ResponseEntity.status(200).body(gettingRespose);
//		}
//		return ResponseEntity.status(400).body(null);

	}
	@GetMapping("/adminGetAllUsers")
	public ResponseEntity<List<User>> gettingAllUsers() {
		System.out.println("get user all");
		List<User> users = adminService.gettingAllUser();
		return ResponseEntity.status(200).body(users);
	}
	@GetMapping("/adminGetUserByName")
	public ResponseEntity<User> gettingUserByName(@RequestParam String name) {
		User user = adminService.gettingUserbyName(name);
		return ResponseEntity.status(200).body(user);
	}
	@DeleteMapping("/adminDeleteUserByName")
	public ResponseEntity<String> deleteUserByName(@RequestParam String name) {
		System.out.println("delete");
		String conMsg = adminService.deletingUserbyName(name);
		return ResponseEntity.status(200).body(conMsg);
	}

	@PutMapping("/adminUpdateUserByName")
	public ResponseEntity<String> updateUserByName(@RequestBody User user) {
//		System.out.println("delete");
		String conMsg = adminService.updateUserbyName(user);
		return ResponseEntity.status(200).body(conMsg);
	} 
}
