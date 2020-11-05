package com.rcl.rclbackend.Reponse;

public class UploadComponentResponse {
	private String message;
	private String componentId;
	private boolean uploadStatus;
	private String downloadUri;
	
	public UploadComponentResponse() {}
	
	public UploadComponentResponse(String message, String componentId, boolean uploadStatus, String downloadUri) {
		super();
		this.message = message;
		this.componentId = componentId;
		this.uploadStatus = uploadStatus;
		this.downloadUri = downloadUri;
	}

	public String getDownloadUri() {
		return downloadUri;
	}

	public void setDownloadUri(String downloadUri) {
		this.downloadUri = downloadUri;
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
