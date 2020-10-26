package com.rcl.rclbackend.DTO;

public class FacetSearchDTO {
	private String userName;
	private String componentName;
	private String domain;
	private String techType;
	private String function;
	private String description;
	private String componentOs;
	private String componentVersion;
	private String input;
	private String output;
	public FacetSearchDTO() {}
	public FacetSearchDTO(String userName, String componentName, String domain, String techType, String function,
			String description, String componentOs, String componentVersion, String input, String output) {
		super();
		this.userName = userName;
		this.componentName = componentName;
		this.domain = domain;
		this.techType = techType;
		this.function = function;
		this.description = description;
		this.componentOs = componentOs;
		this.componentVersion = componentVersion;
		this.input = input;
		this.output = output;
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
