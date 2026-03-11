package com.cls.erp.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cls.erp.model.Permission;
import com.cls.erp.model.Profile;
import com.cls.erp.repository.PermissionRepository;
import com.cls.erp.repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepo;
    
    @Autowired
    private PermissionRepository permissionRepo;
    
	public Profile getProfileByParameter(String developerName) {
		return profileRepo.findByDeveloperName(developerName);
	}
	
	public List<Profile> getProfiles(){
		return profileRepo.findAll();
	}
	
	public Profile getProfileById(String Id) {
		return profileRepo.findById(Id).orElse(null);
	}
	
	public void deleteProfileById(String Id) throws Exception {
		
		if(!profileRepo.existsById(Id)) throw new Exception("Nenhum registro foi encontrado.");
		profileRepo.deleteById(Id);
	}
	
	public Profile update(Map<String, Object> data){
		Profile profileToUpdate = profileRepo.findById(data.get("id").toString()).orElse(null);
		
		if(profileToUpdate != null) {
			profileToUpdate.setName(data.get("name").toString());
			profileToUpdate.setDescription(data.get("description").toString());
			profileToUpdate.setDeveloperName(data.get("developerName").toString());
		}
		
		profileRepo.save(profileToUpdate);
		
		
		return profileToUpdate;
	}

	public Profile insert(Map<String, Object> data) throws Exception{
		Profile profileToUpdate = new Profile();
		
		if (profileRepo.existsByDeveloperName(data.get("developerName").toString()))
		{
			throw new Exception("DeveloperName já existe!");
		}
		
		if(profileToUpdate != null) {
			profileToUpdate.setName(data.get("name").toString());
			profileToUpdate.setDescription(data.get("description").toString());
			profileToUpdate.setDeveloperName(data.get("developerName").toString());
			
			List<String> permissionIds = (List<String>) data.get("permissions");
 			
			Set<Permission> permissions = permissionRepo.findAllById(permissionIds)
					.stream().collect(Collectors.toSet());
			
			profileToUpdate.setPermissions(permissions);
			
		}
		
		profileRepo.save(profileToUpdate);
		
		
		return profileToUpdate;
	}

}
