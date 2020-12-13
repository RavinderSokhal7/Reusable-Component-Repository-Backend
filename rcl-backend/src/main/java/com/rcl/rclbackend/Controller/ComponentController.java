package com.rcl.rclbackend.Controller;

import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rcl.rclbackend.DTO.FacetSearchDTO;
import com.rcl.rclbackend.DTO.UploadedComponentDTO;
import com.rcl.rclbackend.Model.UploadedComponent;
import com.rcl.rclbackend.Reponse.UploadComponentResponse;
import com.rcl.rclbackend.Service.UploadedComponentService;

@RestController
@RequestMapping(value = "/api/rcl")
@CrossOrigin(value = {"*"}, exposedHeaders = {"Content-Disposition"})
public class ComponentController {
	@Autowired
	private UploadedComponentService uploadedComponentService;
	private String notProvided = "#######################";

	private String getImageUrl(String compId) {
		String imgUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/download/image/")
				.path(compId)
				.toUriString();
		return imgUrl;
	}
	private String getDownloadUri(String compId) {
		String imgUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/download/component/")
				.path(compId)
				.toUriString();
		return imgUrl;
	}
	
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
				sb.append(".");
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
				componentName, domain, techType, function, description, componentOs, componentVersion,
				componentFile.getContentType(), previewImg.getContentType(), previewFile.getContentType(),
				"", "", componentInput, componentOutput);
		UploadedComponent comp = uploadedComponentService.uploadPrivatelyByUser(compDto);
		if(comp == null) {
			response.setMessage("Component already exits!");
			response.setUploadStatus(false);
		}
		else {
			String downloadUri =getDownloadUri(comp.getComponentId());
			response.setDownloadUri(downloadUri);
			String imgUrl = getImageUrl(comp.getComponentId());
			comp.setImgUrl(imgUrl);
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
		UploadedComponentDTO compDto = new UploadedComponentDTO(previewImg, previewFile, componentFile, auth.getName(), componentName, domain, techType, function, description, componentOs, componentVersion,
				componentFile.getContentType(), previewImg.getContentType(), previewFile.getContentType(), "", "", componentInput, componentOutput);
		UploadedComponent comp = uploadedComponentService.uploadPubliclyByUser(compDto);
		if(comp == null) {
			response.setMessage("Component already exits!");
			response.setUploadStatus(false);
		}
		else {
			String downloadUri = getDownloadUri(comp.getComponentId());			
			response.setDownloadUri(downloadUri);
			String imgUrl = getImageUrl(comp.getComponentId());
			comp.setImgUrl(imgUrl);
			response.setComponentId(comp.getComponentId());
			response.setMessage(componentName + " Successfully Uploaded");
			response.setUploadStatus(true);
		}
		return response;
	}
	@GetMapping(value = "/download/component/private")
	public List<UploadedComponent> getAllPrivateComponentsByUser(Authentication auth){
		List<UploadedComponent> ret = uploadedComponentService.getAllPrivateComponentByUser(auth.getName());
		for(UploadedComponent comp : ret) {
			comp.setDownloadUri(getDownloadUri(comp.getComponentId()));			
			comp.setImgUrl(getImageUrl(comp.getComponentId()));
		}
		return ret;
	}
	@GetMapping(value = "/download/component/public")
	public List<UploadedComponent> getAllPublicComponentsByUser(
			Authentication auth){
		List<UploadedComponent> ret = uploadedComponentService.getAllPublicComponentByUser(auth.getName());
		for(UploadedComponent comp : ret) {
			comp.setDownloadUri(getDownloadUri(comp.getComponentId()));			
			comp.setImgUrl(getImageUrl(comp.getComponentId()));
		}
		return ret;
	}
	@GetMapping(value = "/download/component/public/facet")
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
		facetSearchDto.setUserName(auth.getName());
		if(componentName.trim().equals("")) componentName = notProvided;
		if(componentVersion.trim().equals("")) componentVersion = notProvided;
		if(componentOs.trim().equals("")) componentOs = notProvided;
		if(description.trim().equals("")) description = notProvided;
		if(domain.trim().equals("")) domain = notProvided;
		if(function.trim().equals("")) function = notProvided;
		if(techType.trim().equals("")) techType = notProvided;
		if(componentInput.trim().equals("")) componentInput = notProvided;
		if(componentOutput.trim().equals("")) componentOutput = notProvided;
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
		for(UploadedComponent comp : ret) {
			comp.setDownloadUri(getDownloadUri(comp.getComponentId()));			
			comp.setImgUrl(getImageUrl(comp.getComponentId()));
		}
		return ret;
	}
	@GetMapping(value = "/download/component/private/facet")
	public List<UploadedComponent> getAllPrivateComponentsByFacetByUser(@RequestParam("componentName") String componentName,
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
		facetSearchDto.setUserName(auth.getName());
		if(componentName.trim().equals("")) componentName = notProvided;
		if(componentVersion.trim().equals("")) componentVersion = notProvided;
		if(componentOs.trim().equals("")) componentOs = notProvided;
		if(description.trim().equals("")) description = notProvided;
		if(domain.trim().equals("")) domain = notProvided;
		if(function.trim().equals("")) function = notProvided;
		if(techType.trim().equals("")) techType = notProvided;
		if(componentInput.trim().equals("")) componentInput = notProvided;
		if(componentOutput.trim().equals("")) componentOutput = notProvided;
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
		for(UploadedComponent comp : ret) {
			comp.setDownloadUri(getDownloadUri(comp.getComponentId()));			
			comp.setImgUrl(getImageUrl(comp.getComponentId()));
		}
		return ret;
	}
	@GetMapping(value = "/download/component/public/attribute")
	public List<UploadedComponent> getAllPublicComponentsByAttributeAndOneFacetByUser(@RequestParam("componentName") String componentName,
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
		facetSearchDto.setUserName(auth.getName());
		if(componentName.trim().equals("")) componentName = notProvided;
		if(componentVersion.trim().equals("")) componentVersion = notProvided;
		if(componentOs.trim().equals("")) componentOs = notProvided;
		if(description.trim().equals("")) description = notProvided;
		if(domain.trim().equals("")) domain = notProvided;
		if(function.trim().equals("")) function = notProvided;
		if(techType.trim().equals("")) techType = notProvided;
		if(componentInput.trim().equals("")) componentInput = notProvided;
		if(componentOutput.trim().equals("")) componentOutput = notProvided;
		facetSearchDto.setComponentName(componentName);
		facetSearchDto.setComponentVersion(componentVersion);
		facetSearchDto.setComponentOs(componentOs);
		facetSearchDto.setDescription(description);
		facetSearchDto.setDomain(domain);
		facetSearchDto.setFunction(function);
		facetSearchDto.setTechType(techType);
		facetSearchDto.setInput(componentInput);
		facetSearchDto.setOutput(componentOutput);
		List<UploadedComponent> ret = uploadedComponentService.getAllPublicComponentByAttributesByUser(facetSearchDto);
		for(UploadedComponent comp : ret) {
			comp.setDownloadUri(getDownloadUri(comp.getComponentId()));			
			comp.setImgUrl(getImageUrl(comp.getComponentId()));
		}
		return ret;
	}
	@GetMapping(value = "/download/component/private/attribute")
	public List<UploadedComponent> getAllPrivateComponentsByAttributeAndOneFacetByUser(@RequestParam("componentName") String componentName,
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
		if(componentName.trim().equals("")) componentName = notProvided;
		if(componentVersion.trim().equals("")) componentVersion = notProvided;
		if(componentOs.trim().equals("")) componentOs = notProvided;
		if(description.trim().equals("")) description = notProvided;
		if(domain.trim().equals("")) domain = notProvided;
		if(function.trim().equals("")) function = notProvided;
		if(techType.trim().equals("")) techType = notProvided;
		if(componentInput.trim().equals("")) componentInput = notProvided;
		if(componentOutput.trim().equals("")) componentOutput = notProvided;
		facetSearchDto.setUserName(auth.getName());
		facetSearchDto.setComponentName(componentName);
		facetSearchDto.setComponentVersion(componentVersion);
		facetSearchDto.setComponentOs(componentOs);
		facetSearchDto.setDescription(description);
		facetSearchDto.setDomain(domain);
		facetSearchDto.setFunction(function);
		facetSearchDto.setTechType(techType);
		facetSearchDto.setInput(componentInput);
		facetSearchDto.setOutput(componentOutput);
		List<UploadedComponent> ret = uploadedComponentService.getAllPrivateComponentByAttributesByUser(facetSearchDto);
		for(UploadedComponent comp : ret) {
			comp.setDownloadUri(getDownloadUri(comp.getComponentId()));			
			comp.setImgUrl(getImageUrl(comp.getComponentId()));
		}
		return ret;
	}
	@GetMapping(value = "/download/component/user/{componentId}")
	public ResponseEntity<Resource> downloadComponentWithIdAndUser(@PathVariable String componentId, Authentication auth){
		UploadedComponent uploadedComponent = uploadedComponentService.downloadComponentByIdByUser(auth.getName(), componentId);		
		if(uploadedComponent==null) {
			return null;
		}
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(uploadedComponent.getComponentFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachments; filename="+uploadedComponent.getComponentName())
				.body(new ByteArrayResource(uploadedComponent.getComponentFile()));
	}
	
	@GetMapping(value = "/download/component/public/all")
	public List<UploadedComponent> getAllPublicComponents(){
		List<UploadedComponent> ret = uploadedComponentService.getAllPublicComponent();
		for(UploadedComponent comp : ret) {
			comp.setDownloadUri(getDownloadUri(comp.getComponentId()));			
			comp.setImgUrl(getImageUrl(comp.getComponentId()));
		}
		return ret;
	}
	
	@GetMapping(value = "/static/component/{componentId}")
	public UploadedComponent getComponentById(@PathVariable String componentId) {
		UploadedComponent uploadedComponent = uploadedComponentService.downloadComponentById(componentId);
	
		if(uploadedComponent != null) {
			uploadedComponent.setImgUrl(getImageUrl(componentId));
			uploadedComponent.setDownloadUri(getDownloadUri(componentId));
		}
		
		return uploadedComponent;
	}
}
