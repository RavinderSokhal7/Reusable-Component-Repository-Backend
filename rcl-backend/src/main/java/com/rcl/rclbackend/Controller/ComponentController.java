package com.rcl.rclbackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rcl.rclbackend.DTO.UploadedComponentDTO;
import com.rcl.rclbackend.Model.UploadedComponent;
import com.rcl.rclbackend.Reponse.UploadComponentResponse;
import com.rcl.rclbackend.Service.UploadedComponentService;
import com.rcl.rclbackend.Service.UserService;

@RestController
@RequestMapping(value = "/api/rcl")
public class ComponentController {
	@Autowired
	private UserService userService;
	@Autowired
	private UploadedComponentService uploadedComponentService;
	
	@PostMapping(value = "/upload/private/{username}")
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
	@PostMapping(value = "/upload/public/{username}")
	public UploadComponentResponse uploadPubliclyByUser(@PathVariable String username,
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
		UploadedComponent comp = uploadedComponentService.uploadPubliclyByUser(compDto);
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
	@GetMapping(value = "/download/private/{username}")
	public List<UploadedComponent> getAllPrivateComponentsByUser(@PathVariable String username){
		List<UploadedComponent> ret = uploadedComponentService.getAllPrivateComponentByUser(username);
		return ret;
	}
	@GetMapping(value = "/download/public/{username}")
	public List<UploadedComponent> getAllPublicComponentsByUser(@PathVariable String username){
		List<UploadedComponent> ret = uploadedComponentService.getAllPublicComponentByUser(username);
		return ret;
	}
}
