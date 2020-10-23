package com.rcl.rclbackend.Service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.rcl.rclbackend.DTO.UploadedComponentDTO;
import com.rcl.rclbackend.Model.UploadedComponent;

public interface UploadedComponentService {
	public UploadedComponent uploadPrivatelyByUser(UploadedComponentDTO uploadedComponentDto) throws UsernameNotFoundException;
	public UploadedComponent uploadPubliclyByUser(UploadedComponentDTO uploadedComponentDto) throws UsernameNotFoundException;
	public List<UploadedComponent> getAllPublicComponent();
	public List<UploadedComponent> getAllPrivateComponentByUser(String userName) throws UsernameNotFoundException;
}
