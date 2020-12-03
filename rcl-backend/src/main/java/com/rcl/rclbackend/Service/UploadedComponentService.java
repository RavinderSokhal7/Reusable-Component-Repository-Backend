package com.rcl.rclbackend.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rcl.rclbackend.DTO.FacetSearchDTO;
import com.rcl.rclbackend.DTO.UploadedComponentDTO;
import com.rcl.rclbackend.Model.UploadedComponent;

public interface UploadedComponentService {
	public UploadedComponent uploadPrivatelyByUser(UploadedComponentDTO uploadedComponentDto) throws UsernameNotFoundException;
	public UploadedComponent uploadPubliclyByUser(UploadedComponentDTO uploadedComponentDto) throws UsernameNotFoundException;
	public UploadedComponent downloadComponentByIdByUser(String userName, String componentId) throws UsernameNotFoundException;
	public List<UploadedComponent> getAllPublicComponent();
	public List<UploadedComponent> getAllPrivateComponentByUser(String userName) throws UsernameNotFoundException;
	public List<UploadedComponent> getAllPublicComponentByUser(String username) throws UsernameNotFoundException;
	public List<UploadedComponent> getAllPublicComponentByFacetAndAttributesByUser(FacetSearchDTO facetSearchDto) throws UsernameNotFoundException;
	public List<UploadedComponent> getAllPrivateComponentByFacetAndAttributesByUser(FacetSearchDTO facetSearchDto) throws UsernameNotFoundException;
	public List<UploadedComponent> getAllPublicComponentByAttributesByUser(FacetSearchDTO facetSearchDto);
	public List<UploadedComponent> getAllPrivateComponentByAttributesByUser(FacetSearchDTO facetSearchDto);
	public List<UploadedComponent> getAllComponentByFacetAndAttributesByUser(FacetSearchDTO facetSearchDto);
	public UploadedComponent downloadComponentById(String componentId);
}
