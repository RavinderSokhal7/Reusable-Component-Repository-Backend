package com.rcl.rclbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.rcl.rclbackend.Repository")
public class RclBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RclBackendApplication.class, args);
	}

}
