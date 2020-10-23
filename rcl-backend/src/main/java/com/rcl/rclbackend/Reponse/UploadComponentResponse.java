package com.rcl.rclbackend.Reponse;

public class UploadComponentResponse {
	private String message;
	private String componentId;
	private boolean uploadStatus;
	
	public UploadComponentResponse() {}

	public UploadComponentResponse(String message, String componentId, boolean uploadStatus) {
		super();
		this.message = message;
		this.componentId = componentId;
		this.uploadStatus = uploadStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public boolean isUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(boolean uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	
}
