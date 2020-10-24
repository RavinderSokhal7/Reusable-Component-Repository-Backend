package com.rcl.rclbackend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rcl.rclbackend.Model.UploadedComponent;

@Repository
public interface UploadedComponentRepository extends JpaRepository<UploadedComponent, String> {
	public List<UploadedComponent> findByUserUserName(String userName);
	@Query("SELECT t FROM UploadedComponent t where t.isPublic = false and t.user.userName = :userName")
	public List<UploadedComponent> findPrivateComponentByUser(@Param("userName") String userName);

	@Query("SELECT t FROM UploadedComponent t where t.isPublic = true and t.user.userName = :userName")
	public List<UploadedComponent> findPublicComponentByUser(@Param("userName") String userName);
}
