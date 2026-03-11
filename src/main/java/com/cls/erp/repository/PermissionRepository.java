package com.cls.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cls.erp.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, String>{

	public Permission findByDeveloperName(String developerName);
	
	public boolean existsByDeveloperName(String developerName);
}
