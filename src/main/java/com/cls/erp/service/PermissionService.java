package com.cls.erp.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cls.erp.model.Permission;
import com.cls.erp.repository.PermissionRepository;

@Service
public class PermissionService {
	
    @Autowired
    private PermissionRepository repository;

	public List<Permission> getPermissions(){
		return repository.findAll();
	}
	
	public Permission getPermission(String Id){
		return repository.findById(Id).orElse(null);
	}
	
	public Permission insert(Map<String, Object> data) throws Exception{
		Permission permissionToInsert = new Permission();
		
		if(permissionToInsert != null) {
			permissionToInsert.setName(data.get("Name").toString());
			permissionToInsert.setDescription(data.get("Description").toString());
			permissionToInsert.setDeveloperName(data.get("DeveloperName").toString());
			permissionToInsert.setCanEdit((boolean) data.get("canEdit"));
			permissionToInsert.setCanDelete((boolean) data.get("canDelete"));
			permissionToInsert.setCanInsert((boolean) data.get("canInsert"));
			permissionToInsert.setCanRead((boolean) data.get("canRead"));
		}
		
		repository.save(permissionToInsert);
		
		
		return permissionToInsert;
	}
	
	public Permission update(Map<String, Object> data) throws Exception{
		Permission permissionToUpdate = repository.findById(data.get("id").toString()).orElse(null);
		
		if(permissionToUpdate != null) {
			permissionToUpdate.setName(data.get("Name").toString());
			permissionToUpdate.setDescription(data.get("Description").toString());
			permissionToUpdate.setDeveloperName(data.get("DeveloperName").toString());
			permissionToUpdate.setCanEdit((boolean) data.get("canEdit"));
			permissionToUpdate.setCanDelete((boolean) data.get("canDelete"));
			permissionToUpdate.setCanInsert((boolean) data.get("canInsert"));
			permissionToUpdate.setCanRead((boolean) data.get("canRead"));
			permissionToUpdate.setUpdatedAt(OffsetDateTime.parse((String) data.get("updatedAt")));
		}
		
		repository.save(permissionToUpdate);
		
		
		return permissionToUpdate;
	}

	public void delete(String Id) {
		System.out.println(Id);
		repository.deleteById(Id);
	}
}
