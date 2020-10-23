package com.rcl.rclbackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rcl.rclbackend.DTO.UploadedComponentDTO;
import com.rcl.rclbackend.Model.UploadedComponent;
import com.rcl.rclbackend.Model.User;
import com.rcl.rclbackend.Reponse.UploadComponentResponse;
import com.rcl.rclbackend.Service.UploadedComponentService;
import com.rcl.rclbackend.Service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UploadedComponentService uploadedComponentService;
	
	@PostMapping(value = "/addUser")
	public void addUser(@RequestBody User user) {
		userService.saveUser(user);
	}
	@GetMapping(value = "/test")
	public String testThis(@RequestBody String ret) {
		return ret;
	}
	@PostMapping(value = "/api/rcl/upload/private/{username}")
	public UploadComponentResponse uploadPrivatelyByUser(@PathVariable String username,
			@RequestParam("previewImg") MultipartFile previewImg,
			@RequestParam("componentFile") MultipartFile componentFile,
			@RequestParam("previewFile") MultipartFile previewFile,
			@RequestParam("componentName") String componentName,
			@RequestParam("domain") String domain,
			@RequestParam("techType") String techType,
			@RequestParam("function") String function,
			@RequestParam("description") String description
			) {
		UploadComponentResponse response = new UploadComponentResponse();
		UploadedComponentDTO compDto = new UploadedComponentDTO(previewImg, previewFile, componentFile,
				username, componentName, domain, techType, function, description);
		UploadedComponent comp = uploadedComponentService.uploadPrivatelyByUser(compDto);
		if(comp == null) {
			response.setMessage("Some error occured!");
			response.setUploadStatus(false);
		}
		else {
			response.setComponentId(comp.getComponentId());
			response.setMessage(componentName + " Successfully Uploaded");
			response.setUploadStatus(true);
		}
		return response;
	}
	@GetMapping(value = "/api/rcl/download/private/{username}")
	public List<UploadedComponent> getAllPrivateComponentsByUser(@PathVariable String username){
		List<UploadedComponent> ret = uploadedComponentService.getAllPrivateComponentByUser(username);
		return ret;
	}
}
