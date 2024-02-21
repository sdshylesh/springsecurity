package com.dextrispoc.dextris.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dextrispoc.dextris.entity.User;
import com.dextrispoc.dextris.service.UserService;

@RestController
@RequestMapping("/creatingUser")
@CrossOrigin
public class UserController {
 @Autowired
  private  UserService userService;
	@PostMapping("/user") 
	public ResponseEntity<String> creatingUser(@RequestBody User user)
	{
	
			String gettingRespose=userService.creatingUser(user);
			if(gettingRespose.equals("saved"))
			{
				System.out.println("this is user controller");
				return ResponseEntity.status(200).body(gettingRespose);
			}
			return ResponseEntity.status(400).body(null);
			
	}
}
