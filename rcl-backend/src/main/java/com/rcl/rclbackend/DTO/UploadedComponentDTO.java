package com.rcl.rclbackend.DTO;

import org.springframework.web.multipart.MultipartFile;

public class UploadedComponentDTO {
	private MultipartFile previewImg;
	private MultipartFile previewFile;
	private MultipartFile componentFile;
	private String userName;
	private String componentName;
	private String domain;
	private String techType;
	private String function;
	private String description;
	private String componentOs;
	private String componentVersion;
	private String componentFileType;
	private String previewImgType;
	private String previewFileType;
	private String downloadUri;
	private String imgUrl;
	private String componentInput;
	private String componentOutput;
	public UploadedComponentDTO() {}
	
	public UploadedComponentDTO(MultipartFile previewImg, MultipartFile previewFile, MultipartFile componentFile,
			String userName, String componentName, String domain, String techType, String function, String description,
			String componentOs, String componentVersion, String componentFileType, String previewImgType,
			String previewFileType, String downloadUri, String imgUrl, String input, String output) {
		super();
		this.previewImg = previewImg;
		this.previewFile = previewFile;
		this.componentFile = componentFile;
		this.userName = userName;
		this.componentName = componentName;
		this.domain = domain;
		this.techType = techType;
		this.function = function;
		this.description = description;
		this.componentOs = componentOs;
		this.componentVersion = componentVersion;
		this.componentFileType = componentFileType;
		this.previewImgType = previewImgType;
		this.previewFileType = previewFileType;
		this.downloadUri = downloadUri;
		this.imgUrl = imgUrl;
		this.componentInput = input;
		this.componentOutput = output;
	}

	public MultipartFile getPreviewImg() {
		return previewImg;
	}
	public void setPreviewImg(MultipartFile previewImg) {
		this.previewImg = previewImg;
	}
	public MultipartFile getPreviewFile() {
		return previewFile;
	}
	public void setPreviewFile(MultipartFile previewFile) {
		this.previewFile = previewFile;
	}
	public MultipartFile getComponentFile() {
		return componentFile;
	}
	public void setComponentFile(MultipartFile componentFile) {
		this.componentFile = componentFile;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getTechType() {
		return techType;
	}
	public void setTechType(String techType) {
		this.techType = techType;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComponentOs() {
		return componentOs;
	}
	public void setComponentOs(String componentOs) {
		this.componentOs = componentOs;
	}
	public String getComponentVersion() {
		return componentVersion;
	}
	public void setComponentVersion(String componentVersion) {
		this.componentVersion = componentVersion;
	}
	public String getComponentFileType() {
		return componentFileType;
	}
	public void setComponentFileType(String componentFileType) {
		this.componentFileType = componentFileType;
	}
	public String getPreviewImgType() {
		return previewImgType;
	}
	public void setPreviewImgType(String previewImgType) {
		this.previewImgType = previewImgType;
	}
	public String getPreviewFileType() {
		return previewFileType;
	}
	public void setPreviewFileType(String previewFileType) {
		this.previewFileType = previewFileType;
	}
	public String getDownloadUri() {
		return downloadUri;
	}
	public void setDownloadUri(String downloadUri) {
		this.downloadUri = downloadUri;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
		
	public String getComponentInput() {
		return componentInput;
	}

	public void setComponentInput(String componentInput) {
		this.componentInput = componentInput;
	}

	public String getComponentOutput() {
		return componentOutput;
	}

	public void setComponentOutput(String componentOutput) {
		this.componentOutput = componentOutput;
	}

	@Override
	public String toString() {
		return "UploadedComponentDTO [previewImg=" + previewImg + ", previewFile=" + previewFile + ", componentFile="
				+ componentFile + ", userName=" + userName + ", componentName=" + componentName + ", domain=" + domain
				+ ", techType=" + techType + ", function=" + function + ", description=" + description
				+ ", componentOs=" + componentOs + ", componentVersion=" + componentVersion + ", componentFileType="
				+ componentFileType + ", previewImgType=" + previewImgType + ", previewFileType=" + previewFileType
				+ ", downloadUri=" + downloadUri + ", imgUrl=" + imgUrl + ", componentInput=" + componentInput
				+ ", componentOutput=" + componentOutput + "]";
	}
	
}
