package com.cls.erp.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cls.erp.model.Profile;
import com.cls.erp.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileResource {
	
    private final ProfileService profileService;
    
    public ProfileResource(ProfileService profileService){
    	this.profileService = profileService;
    }

	@GetMapping
	public ResponseEntity<Map<String, Object>> getProfiles() {
		Map<String, Object> response = new HashMap<>();
		
		try {
			List<Profile> profiles = profileService.getProfiles();
	        response.put("success", true);
	        response.put("data", profiles);
	        response.put("message", "Perfis encontrados!");
	        
	        return ResponseEntity.ok(response);
		
		} catch (Exception exception) {
		
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
		}
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<Map<String, Object>> getProfileByParameter(@PathVariable String Id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			Profile profile = profileService.getProfileById(Id);
	        response.put("success", true);
	        response.put("data", profile);
	        response.put("message", "Perfis encontrados!");
	        
	        return ResponseEntity.ok(response);
		
		} catch (Exception exception) {
		
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
		}
	}
	
	@DeleteMapping("/delete/{Id}")
	public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable String Id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			profileService.deleteProfileById(Id);
	        response.put("success", true);
	        response.put("message", "Perfil deletado com sucesso!");
	        
	        return ResponseEntity.ok(response);
		
		} catch (Exception exception) {
		
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
		}
	}
	
    @PutMapping("/update")
	public ResponseEntity<Map<String, Object>> updateProfile(@RequestBody Map<String, Object> profileJson) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			Profile profile = profileService.update(profileJson);
			
	        response.put("success", true);
	        response.put("profile", profile);
	        response.put("message", "Perfil atualizado com sucesso!");
	        
	        return ResponseEntity.ok(response);
			
		} catch (DataIntegrityViolationException exception) {
			
			throw exception;
		
		} catch (Exception exception) {
			
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
		}
	}

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createNewProfile(@RequestBody Map<String, Object> profileJson) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			Profile profile = profileService.insert(profileJson);
			
	        response.put("success", true);
	        response.put("profile", profile);
	        response.put("message", "Perfil inserido com sucesso!");
	        
	        return ResponseEntity.ok(response);
			
		} catch (DataIntegrityViolationException exception) {
			
			throw exception;
		
		}  catch (Exception exception) {
			
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
		}
    }
	
}
