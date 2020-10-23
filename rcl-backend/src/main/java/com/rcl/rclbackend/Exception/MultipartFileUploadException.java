package com.rcl.rclbackend.Exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;


@RestControllerAdvice
public class MultipartFileUploadException {
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String handleFileUploadException( MaxUploadSizeExceededException exception, HttpServletRequest request, HttpServletResponse response) {
		return "File size too big! Upload file within 1MB size";
	}
}