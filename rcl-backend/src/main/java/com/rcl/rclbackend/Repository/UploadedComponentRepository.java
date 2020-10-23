package com.rcl.rclbackend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rcl.rclbackend.Model.UploadedComponent;

@Repository
public interface UploadedComponentRepository extends JpaRepository<UploadedComponent, String> {
	public List<UploadedComponent> findByUserUserName(String userName);
}
