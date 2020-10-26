package com.rcl.rclbackend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rcl.rclbackend.Model.User;
import com.rcl.rclbackend.Service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/admin/addUser")
	public void addUser(@RequestBody User user) {
		userService.saveUser(user);
	}
}