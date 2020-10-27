package com.rcl.rclbackend.Reponse;

public class UserResponse {
	private String message;
	private Long userId;
	private String userName;
	private boolean addStatus;
	public UserResponse() {}
	public UserResponse(String message, Long userId, String userName, boolean uploadStatus) {
		super();
		this.message = message;
		this.userId = userId;
		this.userName = userName;
		this.addStatus = uploadStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isUploadStatus() {
		return addStatus;
	}
	public void setAddStatus(boolean addStatus) {
		this.addStatus = addStatus;
	}
}
