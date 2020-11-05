package com.rcl.rclbackend.ServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rcl.rclbackend.DTO.FacetSearchDTO;
import com.rcl.rclbackend.DTO.UploadedComponentDTO;
import com.rcl.rclbackend.Model.UploadedComponent;
import com.rcl.rclbackend.Model.User;
import com.rcl.rclbackend.Repository.UploadedComponentRepository;
import com.rcl.rclbackend.Repository.UserRepository;
import com.rcl.rclbackend.Service.UploadedComponentService;

@Service
public class UploadedComponentServiceImpl implements UploadedComponentService {

	@Autowired
	private UploadedComponentRepository uploadedComponentRepo;
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UploadedComponent uploadPrivatelyByUser(UploadedComponentDTO uploadedComponentDto) throws UsernameNotFoundException {
		UploadedComponent uploadedComponent = new UploadedComponent();
		try {
			// adding User
			Optional<User> user = userRepo.findByUserName(uploadedComponentDto.getUserName());
			//check user later
			if(user.isPresent())
				uploadedComponent.setUser(user.get());
			else {
				throw new UsernameNotFoundException(uploadedComponentDto.getUserName() + "Not Found!");
			}
			
//			uploadedComponent.setPreviewImg(uploadedComponentDto.getPreviewImg().getBytes());
//			uploadedComponent.setPreviewFile(uploadedComponentDto.getPreviewFile().getBytes());
//			uploadedComponent.setComponentFile(uploadedComponentDto.getComponentFile().getBytes());
//			uploadedComponent.setComponentName(uploadedComponentDto.getComponentName());
//			uploadedComponent.setDescription(uploadedComponentDto.getDescription());
			uploadedComponent.setDomain(uploadedComponentDto.getDomain());
			uploadedComponent.setFunction(uploadedComponentDto.getFunction());
			uploadedComponent.setTechType(uploadedComponentDto.getTechType());
			uploadedComponent.setPublic(false);
			uploadedComponent.setOutput(uploadedComponentDto.getOutput());
			uploadedComponent.setInput(uploadedComponentDto.getInput());
			uploadedComponent.setComponentVersion(uploadedComponentDto.getComponentVersion());
			uploadedComponent.setComponentOs(uploadedComponentDto.getComponentOs());
			Example<UploadedComponent> example = Example.of(uploadedComponent);
			if(uploadedComponentRepo.exists(example))
				return null;
			uploadedComponent.setPreviewImg(uploadedComponentDto.getPreviewImg().getBytes());
			uploadedComponent.setPreviewFile(uploadedComponentDto.getPreviewFile().getBytes());
			uploadedComponent.setComponentFile(uploadedComponentDto.getComponentFile().getBytes());
			uploadedComponent.setPreviewFileType(uploadedComponentDto.getPreviewFile().getContentType());
			uploadedComponent.setComponentFileType(uploadedComponentDto.getComponentFile().getContentType());
			uploadedComponent.setPreviewImgType(uploadedComponentDto.getPreviewImg().getContentType());
			uploadedComponent.setComponentName(uploadedComponentDto.getComponentName());
			uploadedComponent.setDescription(uploadedComponentDto.getDescription());
			UploadedComponent uploadedComponentToRet = uploadedComponentRepo.save(uploadedComponent);
			return uploadedComponentToRet;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public UploadedComponent uploadPubliclyByUser(UploadedComponentDTO uploadedComponentDto)
			throws UsernameNotFoundException {
		UploadedComponent uploadedComponent = new UploadedComponent();
		try {
			// adding User
			Optional<User> user = userRepo.findByUserName(uploadedComponentDto.getUserName());
			//check user later
			if(user.isPresent())
				uploadedComponent.setUser(user.get());
			else {
				throw new UsernameNotFoundException(uploadedComponentDto.getUserName() + "Not Found!");
			}
			
//			uploadedComponent.setPreviewImg(uploadedComponentDto.getPreviewImg().getBytes());
//			uploadedComponent.setPreviewFile(uploadedComponentDto.getPreviewFile().getBytes());
//			uploadedComponent.setComponentFile(uploadedComponentDto.getComponentFile().getBytes());
//			uploadedComponent.setComponentName(uploadedComponentDto.getComponentName());
//			uploadedComponent.setDescription(uploadedComponentDto.getDescription());
			uploadedComponent.setDomain(uploadedComponentDto.getDomain());
			uploadedComponent.setFunction(uploadedComponentDto.getFunction());
			uploadedComponent.setTechType(uploadedComponentDto.getTechType());
			uploadedComponent.setPublic(true);
			uploadedComponent.setOutput(uploadedComponentDto.getOutput());
			uploadedComponent.setInput(uploadedComponentDto.getInput());
			uploadedComponent.setComponentVersion(uploadedComponentDto.getComponentVersion());
			uploadedComponent.setComponentOs(uploadedComponentDto.getComponentOs());
			Example<UploadedComponent> example = Example.of(uploadedComponent);
			if(uploadedComponentRepo.exists(example))
				return null;
			uploadedComponent.setPreviewImg(uploadedComponentDto.getPreviewImg().getBytes());
			uploadedComponent.setPreviewFile(uploadedComponentDto.getPreviewFile().getBytes());
			uploadedComponent.setComponentFile(uploadedComponentDto.getComponentFile().getBytes());
			uploadedComponent.setPreviewFileType(uploadedComponentDto.getPreviewFile().getContentType());
			uploadedComponent.setComponentFileType(uploadedComponentDto.getComponentFile().getContentType());
			uploadedComponent.setPreviewImgType(uploadedComponentDto.getPreviewImg().getContentType());
			uploadedComponent.setComponentName(uploadedComponentDto.getComponentName());
			uploadedComponent.setDescription(uploadedComponentDto.getDescription());
			UploadedComponent uploadedComponentToRet = uploadedComponentRepo.save(uploadedComponent);
			return uploadedComponentToRet;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<UploadedComponent> getAllPublicComponent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UploadedComponent> getAllPrivateComponentByUser(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByUserName(userName);
		if(user.isPresent()==false) {
			throw new UsernameNotFoundException(userName + " Not Found!");
		}
		// CHange to only Private
		List<UploadedComponent> list = uploadedComponentRepo.findPrivateComponentByUser(userName);
		return list;
	}

	@Override
	public List<UploadedComponent> getAllPublicComponentByUser(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByUserName(userName);
		if(user.isPresent()==false) {
			throw new UsernameNotFoundException(userName + " Not Found!");
		}
		// CHange to only Public
		List<UploadedComponent> list = uploadedComponentRepo.findPublicComponentByUser(userName);
		return list;
	}

	@Override
	public List<UploadedComponent> getAllPublicComponentByFacetAndAttributesByUser(FacetSearchDTO facetSearchDto)
			throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByUserName(facetSearchDto.getUserName());
		if(user.isPresent()==false) {
			throw new UsernameNotFoundException(facetSearchDto.getUserName() + "Not Found!");
		}
		List<UploadedComponent> list = uploadedComponentRepo.findPublicComponentByFacetAndAttributesByUser(
				facetSearchDto.getTechType(), 
				facetSearchDto.getFunction()); 
//				facetSearchDto.getComponentOs(), 
//				facetSearchDto.getComponentVersion(), 
//				facetSearchDto.getInput(), 
//				facetSearchDto.getOutput(), 
//				facetSearchDto.getComponentName(), 
//				facetSearchDto.getDomain(), 
//				facetSearchDto.getDescription());
		return list;
	}

	@Override
	public List<UploadedComponent> getAllPrivateComponentByFacetAndAttributesByUser(FacetSearchDTO facetSearchDto)
			throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByUserName(facetSearchDto.getUserName());
		if(user.isPresent()==false) {
			throw new UsernameNotFoundException(facetSearchDto.getUserName() + "Not Found!");
		}
		List<UploadedComponent> list = uploadedComponentRepo.findPrivateComponentByFacetAndAttributesByUser(facetSearchDto.getUserName(),
				facetSearchDto.getTechType(), 
				facetSearchDto.getFunction());
//				facetSearchDto.getComponentOs(), 
//				facetSearchDto.getComponentVersion(), 
//				facetSearchDto.getInput(), 
//				facetSearchDto.getOutput(), 
//				facetSearchDto.getComponentName(), 
//				facetSearchDto.getDomain(), 
//				facetSearchDto.getDescription());
		return list;
	}

	@Override
	public List<UploadedComponent> getAllPublicComponentByAttributesByUser(FacetSearchDTO facetSearchDto) {
		Optional<User> user = userRepo.findByUserName(facetSearchDto.getUserName());
		if(user.isPresent()==false) {
			throw new UsernameNotFoundException(facetSearchDto.getUserName() + "Not Found!");
		}
		List<UploadedComponent> list = uploadedComponentRepo.findPublicComponentByAttributesByUser(
				facetSearchDto.getTechType(), 
				facetSearchDto.getFunction(), 
				facetSearchDto.getComponentOs(), 
				facetSearchDto.getComponentVersion(), 
				facetSearchDto.getInput(), 
				facetSearchDto.getOutput(), 
				facetSearchDto.getComponentName(), 
				facetSearchDto.getDomain(), 
				facetSearchDto.getDescription());
		return list;
	}

	@Override
	public List<UploadedComponent> getAllPrivateComponentByAttributesByUser(FacetSearchDTO facetSearchDto) {
		Optional<User> user = userRepo.findByUserName(facetSearchDto.getUserName());
		if(user.isPresent()==false) {
			throw new UsernameNotFoundException(facetSearchDto.getUserName() + "Not Found!");
		}
		List<UploadedComponent> list = uploadedComponentRepo.findPrivateComponentByAttributesByUser(facetSearchDto.getUserName(),
				facetSearchDto.getTechType(), 
				facetSearchDto.getFunction(), 
				facetSearchDto.getComponentOs(), 
				facetSearchDto.getComponentVersion(), 
				facetSearchDto.getInput(), 
				facetSearchDto.getOutput(), 
				facetSearchDto.getComponentName(), 
				facetSearchDto.getDomain(), 
				facetSearchDto.getDescription());
		return list;
	}

	@Override
	public List<UploadedComponent> getAllComponentByFacetAndAttributesByUser(FacetSearchDTO facetSearchDto) {
		Optional<User> user = userRepo.findByUserName(facetSearchDto.getUserName());
		if(user.isPresent()==false) {
			throw new UsernameNotFoundException(facetSearchDto.getUserName() + "Not Found!");
		}
		List<UploadedComponent> list = uploadedComponentRepo.findAllComponentByFacetAndAttributesByUser(facetSearchDto.getUserName(),
				facetSearchDto.getTechType(), 
				facetSearchDto.getFunction()); 
//				facetSearchDto.getComponentOs(), 
//				facetSearchDto.getComponentVersion(), 
//				facetSearchDto.getInput(), 
//				facetSearchDto.getOutput(), 
//				facetSearchDto.getComponentName(), 
//				facetSearchDto.getDomain(), 
//				facetSearchDto.getDescription());
		return list;
	}

	@Override
	public UploadedComponent downloadComponentByIdByUser(String userName, String componentId)
			throws UsernameNotFoundException {
		Optional<User> user = userRepo.findByUserName(userName);
		if(user.isPresent()==false) {
			throw new UsernameNotFoundException(userName + "Not Found!");
		}
		UploadedComponent uploadedComponent = uploadedComponentRepo.downloadComponentByIdByUser(userName, componentId);
		return uploadedComponent;
	}

}
