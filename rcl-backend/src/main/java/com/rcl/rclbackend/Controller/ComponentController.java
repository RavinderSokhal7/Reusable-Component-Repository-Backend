package com.rcl.rclbackend.Controller;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rcl.rclbackend.DTO.FacetSearchDTO;
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

	private String rectifyVersion(String componentVersion) {
		if(componentVersion==null) return null;
		componentVersion.trim();
		StringBuilder rectifiedVersion = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(componentVersion, ".");
		boolean f = false;
		while(st.hasMoreTokens()) {
			int x = Integer.parseInt(st.nextToken());
			if(f) {
				rectifiedVersion.append(".");
			}
			f = true;
			sb.append(x);
			if(x>0) {
				rectifiedVersion.append(sb);
				sb = new StringBuilder();
			}
		}
		return rectifiedVersion.toString();
	}
	@PostMapping(value = "/upload/component/private")
	public UploadComponentResponse uploadPrivatelyByUser(@RequestParam("previewImg") MultipartFile previewImg,
			@RequestParam("componentFile") MultipartFile componentFile,
			@RequestParam("previewFile") MultipartFile previewFile,
			@RequestParam("componentName") String componentName,
			@RequestParam("domain") String domain,
			@RequestParam("techType") String techType,
			@RequestParam("function") String function,
			@RequestParam("description") String description,
			@RequestParam("componentOs") String componentOs,
			@RequestParam("componentVersion") String componentVersion,
			@RequestParam("componentInput") String componentInput,
			@RequestParam("componentOutput") String componentOutput,
			Authentication auth
			) {
		componentVersion = rectifyVersion(componentVersion);
		UploadComponentResponse response = new UploadComponentResponse();
		UploadedComponentDTO compDto = new UploadedComponentDTO(previewImg, previewFile, componentFile, auth.getName(),
				componentName, domain, techType, function, description, componentOs, componentVersion, componentInput, componentOutput);
		UploadedComponent comp = uploadedComponentService.uploadPrivatelyByUser(compDto);
		if(comp == null) {
			response.setMessage("Component already exits!");
			response.setUploadStatus(false);
		}
		else {
			response.setComponentId(comp.getComponentId());
			response.setMessage(componentName + " Successfully Uploaded");
			response.setUploadStatus(true);
		}
		return response;
	}
	@PostMapping(value = "/upload/component/public")
	public UploadComponentResponse uploadPubliclyByUser(@RequestParam("previewImg") MultipartFile previewImg,
			@RequestParam("componentFile") MultipartFile componentFile,
			@RequestParam("previewFile") MultipartFile previewFile,
			@RequestParam("componentName") String componentName,
			@RequestParam("domain") String domain,
			@RequestParam("techType") String techType,
			@RequestParam("function") String function,
			@RequestParam("description") String description,
			@RequestParam("componentOs") String componentOs,
			@RequestParam("componentVersion") String componentVersion,
			@RequestParam("componentInput") String componentInput,
			@RequestParam("componentOutput") String componentOutput,
			Authentication auth
			) {
		componentVersion = rectifyVersion(componentVersion);
		UploadComponentResponse response = new UploadComponentResponse();
		UploadedComponentDTO compDto = new UploadedComponentDTO(previewImg, previewFile, componentFile, auth.getName(),
				componentName, domain, techType, function, description, componentOs, componentVersion, componentInput, componentOutput);
		UploadedComponent comp = uploadedComponentService.uploadPubliclyByUser(compDto);
		if(comp == null) {
			response.setMessage("Component already exits!");
			response.setUploadStatus(false);
		}
		else {
			response.setComponentId(comp.getComponentId());
			response.setMessage(componentName + " Successfully Uploaded");
			response.setUploadStatus(true);
		}
		return response;
	}
	@GetMapping(value = "/download/component/private")
	public List<UploadedComponent> getAllPrivateComponentsByUser(Authentication auth){
		List<UploadedComponent> ret = uploadedComponentService.getAllPrivateComponentByUser(auth.getName());
		return ret;
	}
	@GetMapping(value = "/download/component/public")
	public List<UploadedComponent> getAllPublicComponentsByUser(@RequestParam("componentName") String componentName,
			@RequestParam("domain") String domain,
			@RequestParam("techType") String techType,
			@RequestParam("function") String function,
			@RequestParam("description") String description,
			@RequestParam("componentOs") String componentOs,
			@RequestParam("componentVersion") String componentVersion,
			@RequestParam("componentInput") String componentInput,
			@RequestParam("componentOutput") String componentOutput,
			Authentication auth){
		componentVersion = rectifyVersion(componentVersion);
		FacetSearchDTO facetSearchDto = new FacetSearchDTO();
		facetSearchDto.setComponentName(componentName);
		facetSearchDto.setComponentVersion(componentVersion);
		facetSearchDto.setComponentOs(componentOs);
		facetSearchDto.setDescription(description);
		facetSearchDto.setDomain(domain);
		facetSearchDto.setFunction(function);
		facetSearchDto.setTechType(techType);
		facetSearchDto.setInput(componentInput);
		facetSearchDto.setOutput(componentOutput);
		List<UploadedComponent> ret = uploadedComponentService.getAllPublicComponentByFacetAndAttributesByUser(facetSearchDto);
		return ret;
	}
	@GetMapping(value = "/download/component/facet/public")
	public List<UploadedComponent> getAllPublicComponentsByFacetByUser(@RequestParam("componentName") String componentName,
			@RequestParam("domain") String domain,
			@RequestParam("techType") String techType,
			@RequestParam("function") String function,
			@RequestParam("description") String description,
			@RequestParam("componentOs") String componentOs,
			@RequestParam("componentVersion") String componentVersion,
			@RequestParam("componentInput") String componentInput,
			@RequestParam("componentOutput") String componentOutput,
			Authentication auth){
		componentVersion = rectifyVersion(componentVersion);
		FacetSearchDTO facetSearchDto = new FacetSearchDTO();
		facetSearchDto.setComponentName(componentName);
		facetSearchDto.setComponentVersion(componentVersion);
		facetSearchDto.setComponentOs(componentOs);
		facetSearchDto.setDescription(description);
		facetSearchDto.setDomain(domain);
		facetSearchDto.setFunction(function);
		facetSearchDto.setTechType(techType);
		facetSearchDto.setInput(componentInput);
		facetSearchDto.setOutput(componentOutput);
		List<UploadedComponent> ret = uploadedComponentService.getAllPrivateComponentByFacetAndAttributesByUser(facetSearchDto);
		return ret;
	}
}
