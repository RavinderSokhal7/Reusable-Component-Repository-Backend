package com.rcl.rclbackend.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rcl.rclbackend.Model.User;
import com.rcl.rclbackend.Model.UserAdapter;
import com.rcl.rclbackend.Service.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = userService.findUserByUserName(username);
		if(user.isPresent()) return new UserAdapter(user.get());
		throw new UsernameNotFoundException(username + " Not Found!");
	}

}
