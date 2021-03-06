package com.rcl.rclbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.rcl.rclbackend.Repository")
public class RclBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RclBackendApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("http://localhost:3000")
				.allowedHeaders("*")
				.allowedMethods("*")
				.allowCredentials(true)
				.exposedHeaders("Content-Disposition");
			}
		};
	}
}
