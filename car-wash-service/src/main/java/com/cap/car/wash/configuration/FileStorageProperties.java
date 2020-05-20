package com.cap.car.wash.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.cap.car.wash.appconstants.AppConstants;

@ConfigurationProperties(prefix=AppConstants.FILE_PROPERTIES_PREFIX)
public class FileStorageProperties {
	
	private String uploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	
}
