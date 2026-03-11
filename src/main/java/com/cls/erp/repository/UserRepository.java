package com.cls.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cls.erp.model.User;
import com.cls.erp.projection.UserProjection;

public interface UserRepository extends JpaRepository<User, String>{
	
	public User findByUsername(String username);
	
	public UserProjection findProjectById(String Id);
	
}
