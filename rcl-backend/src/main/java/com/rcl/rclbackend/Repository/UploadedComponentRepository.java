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
	
	@Query("SELECT t from UploadedComponent t WHERE "+
			"t.isPublic = false and t.user.userName = :userName "
			+ "and LOWER(t.techType) LIKE LOWER(CONCAT('%',:techType, '%')) "
			+ "and LOWER(t.function) LIKE LOWER(CONCAT('%',:function, '%')) "
			+ "and ( LOWER(t.componentOs) LIKE LOWER(CONCAT('%',:componentOs, '%')) "
			+ "OR LOWER(t.componentVersion) LIKE LOWER(CONCAT('%',:componentVersion, '%')) "
			+ "OR LOWER(t.componentInput) LIKE LOWER(CONCAT('%',:componentInput, '%')) "
			+ "OR LOWER(t.componentOutput) LIKE LOWER(CONCAT('%',:componentOutput, '%')) "
			+ "OR LOWER(t.componentName) LIKE LOWER(CONCAT('%',:componentName, '%')) "
			+ "OR LOWER(t.domain) LIKE LOWER(CONCAT('%',:domain, '%')) "
			+ "OR LOWER(t.description) LIKE LOWER(CONCAT('%',:description, '%')) )" )
	public List<UploadedComponent> findPrivateComponentByFacetAndAttributesByUser(@Param("userName") String userName,
			@Param("techType") String techType,
			@Param("function") String function,
			@Param("componentOs") String componentOs,
			@Param("componentVersion") String componentVersion,
			@Param("componentInput") String componentInput,
			@Param("componentOutput") String componentOutput,
			@Param("componentName") String componentName,
			@Param("domain") String domain,
			@Param("description") String description);

	@Query("SELECT t from UploadedComponent t WHERE "+
			"t.isPublic = true and t.user.userName = :userName "
			+ "and LOWER(t.techType) LIKE LOWER(CONCAT('%',:techType, '%')) "
			+ "and LOWER(t.function) LIKE LOWER(CONCAT('%',:function, '%')) "
			+ "and ( LOWER(t.componentOs) LIKE LOWER(CONCAT('%',:componentOs, '%')) "
			+ "OR LOWER(t.componentVersion) LIKE LOWER(CONCAT('%',:componentVersion, '%')) "
			+ "OR LOWER(t.componentInput) LIKE LOWER(CONCAT('%',:componentInput, '%')) "
			+ "OR LOWER(t.componentOutput) LIKE LOWER(CONCAT('%',:componentOutput, '%')) "
			+ "OR LOWER(t.componentName) LIKE LOWER(CONCAT('%',:componentName, '%')) "
			+ "OR LOWER(t.domain) LIKE LOWER(CONCAT('%',:domain, '%')) "
			+ "OR LOWER(t.description) LIKE LOWER(CONCAT('%',:description, '%')) )" )
	public List<UploadedComponent> findPublicComponentByFacetAndAttributesByUser(@Param("userName") String userName,
			@Param("techType") String techType,
			@Param("function") String function,
			@Param("componentOs") String componentOs,
			@Param("componentVersion") String componentVersion,
			@Param("componentInput") String componentInput,
			@Param("componentOutput") String componentOutput,
			@Param("componentName") String componentName,
			@Param("domain") String domain,
			@Param("description") String description);
	
	@Query("SELECT t from UploadedComponent t WHERE "+
			"t.user.userName = :userName "
			+ "and LOWER(t.techType) LIKE LOWER(CONCAT('%',:techType, '%')) "
			+ "and LOWER(t.function) LIKE LOWER(CONCAT('%',:function, '%')) "
			+ "and ( LOWER(t.componentOs) LIKE LOWER(CONCAT('%',:componentOs, '%')) "
			+ "OR LOWER(t.componentVersion) LIKE LOWER(CONCAT('%',:componentVersion, '%')) "
			+ "OR LOWER(t.componentInput) LIKE LOWER(CONCAT('%',:componentInput, '%')) "
			+ "OR LOWER(t.componentOutput) LIKE LOWER(CONCAT('%',:componentOutput, '%')) "
			+ "OR LOWER(t.componentName) LIKE LOWER(CONCAT('%',:componentName, '%')) "
			+ "OR LOWER(t.domain) LIKE LOWER(CONCAT('%',:domain, '%')) "
			+ "OR LOWER(t.description) LIKE LOWER(CONCAT('%',:description, '%')) )" )
	public List<UploadedComponent> findAllComponentByFacetAndAttributesByUser(@Param("userName") String userName,
			@Param("techType") String techType,
			@Param("function") String function,
			@Param("componentOs") String componentOs,
			@Param("componentVersion") String componentVersion,
			@Param("componentInput") String componentInput,
			@Param("componentOutput") String componentOutput,
			@Param("componentName") String componentName,
			@Param("domain") String domain,
			@Param("description") String description);
	
}
