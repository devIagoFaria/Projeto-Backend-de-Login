package com.cls.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cls.erp.model.Profile;

public interface ProfileRepository  extends JpaRepository<Profile, String> {

	public Profile findByDeveloperName(String developerName);
	
	public boolean existsByDeveloperName(String developerName);
}
