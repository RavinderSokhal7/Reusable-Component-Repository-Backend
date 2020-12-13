package com.rcl.rclbackend.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rcl.rclbackend.Model.AuthenticationRequest;
import com.rcl.rclbackend.Model.AuthenticationResponse;
import com.rcl.rclbackend.Model.RoleNames;
import com.rcl.rclbackend.Model.UploadedComponent;
import com.rcl.rclbackend.Model.User;
import com.rcl.rclbackend.Reponse.UserResponse;
import com.rcl.rclbackend.Service.UploadedComponentService;
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
	
	@Autowired
	private UploadedComponentService uploadedComponentService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			return ResponseEntity.badRequest().build();
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@GetMapping(value = "/user/{username}")
	public User getUserByUsername(@PathVariable String username, Authentication Auth) {
		if(username.equals(Auth.getName())==false) {
			throw new UsernameNotFoundException("Incorrect username");
		}
		Optional<User> user = userService.findUserByUserName(username);
		if(user.isPresent()==false) {
			throw new UsernameNotFoundException("Incorrect username");
		}
		return user.get();
	}
	
	@PostMapping(value = "/register/user")
	public UserResponse addUser(@RequestBody User user) {
		UserResponse response = new UserResponse();
		user.setRoleName(RoleNames.USER.name());
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
	
	@GetMapping(value = "/download/image/{componentId}")
	public ResponseEntity<Resource> downloadImageWithId(@PathVariable String componentId){
		UploadedComponent uploadedComponent = uploadedComponentService.downloadComponentById(componentId);		
		if(uploadedComponent==null) {
			return null;
		}
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(uploadedComponent.getPreviewImgType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachments; filename = "+uploadedComponent.getComponentName())
				.body(new ByteArrayResource(uploadedComponent.getPreviewImg()));
	}
	@GetMapping(value = "/download/component/{componentId}")
	public ResponseEntity<Resource> downloadComponentWithId(@PathVariable String componentId){
		UploadedComponent uploadedComponent = uploadedComponentService.downloadComponentById(componentId);		
		if(uploadedComponent==null) {
			return null;
		}
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(uploadedComponent.getComponentFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachments; filename = "+uploadedComponent.getComponentName())
				.body(new ByteArrayResource(uploadedComponent.getComponentFile()));
	}
}
