package com.rcl.rclbackend.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rcl.rclbackend.Model.User;
import com.rcl.rclbackend.Reponse.UserResponse;
import com.rcl.rclbackend.Service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/admin/addUser")
	public UserResponse addUser(@RequestBody User user) {
		UserResponse response = new UserResponse();
		Optional<User> op = userService.findUserByUserName(user.getUserName());
		if(op.isPresent()) {
			response.setMessage("user with username : " + user.getUserName() + " Already Exits!");
			response.setAddStatus(false);
			response.setUserId(null);
			response.setUserName(user.getUserName());
		}
		else {
			userService.saveUser(user);
			op = userService.findUserByUserName(user.getUserName());
			user = op.get();
			response.setAddStatus(true);
			response.setUserName(user.getUserName());
			response.setUserId(user.getId());
			response.setMessage("User successfully added!");
		}
		return response;
	}
}
