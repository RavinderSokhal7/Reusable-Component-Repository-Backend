package com.rcl.rclbackend.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rcl.rclbackend.Model.AuthenticationRequest;
import com.rcl.rclbackend.Model.AuthenticationResponse;
import com.rcl.rclbackend.Model.User;
import com.rcl.rclbackend.Reponse.UserResponse;
import com.rcl.rclbackend.Service.UserService;
import com.rcl.rclbackend.ServiceImpl.MyUserDetailsService;
import com.rcl.rclbackend.util.JwtUtil;



@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@PostMapping(value = "/register/addUser")
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
