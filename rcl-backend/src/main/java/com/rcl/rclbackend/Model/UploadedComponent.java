package com.rcl.rclbackend.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class UploadedComponent {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name="uuid", strategy = "uuid2")
	private String componentId;
	private String componentName;
	private String domain;
	private String techType;
	@Column(name = "component_function")
	private String function;
	private String description;
	private String componentOs;
	private String componentVersion;
	private String input;
	private String output;
	private boolean isPublic;
	@Lob
	private byte[] previewImg;
	@Lob
	private byte[] previewFile;
	@Lob
	private byte[] componentFile;
	@ManyToOne
	private User user;
	public UploadedComponent() {}
	
	public UploadedComponent(String componentId, String componentName, String domain, String techType, String function,
			String description, String componentOs, String componentVersion, String input, String output,
			boolean isPublic, byte[] previewImg, byte[] previewFile, byte[] componentFile, User user) {
		super();
		this.componentId = componentId;
		this.componentName = componentName;
		this.domain = domain;
		this.techType = techType;
		this.function = function;
		this.description = description;
		this.componentOs = componentOs;
		this.componentVersion = componentVersion;
		this.input = input;
		this.output = output;
		this.isPublic = isPublic;
		this.previewImg = previewImg;
		this.previewFile = previewFile;
		this.componentFile = componentFile;
		this.user = user;
	}

	public String getComponentId() {
		return componentId;
	}
	public void setComponentId(String componentId) {
		this.componentId = componentId;
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
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public byte[] getPreviewImg() {
		return previewImg;
	}
	public void setPreviewImg(byte[] previewImg) {
		this.previewImg = previewImg;
	}
	public byte[] getPreviewFile() {
		return previewFile;
	}
	public void setPreviewFile(byte[] previewFile) {
		this.previewFile = previewFile;
	}
	public byte[] getComponentFile() {
		return componentFile;
	}
	public void setComponentFile(byte[] componentFile) {
		this.componentFile = componentFile;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
	
}
