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

import com.cls.erp.model.Permission;
import com.cls.erp.service.PermissionService;

@RestController
@RequestMapping("/permission")
public class PermissionResource {
	
	private final PermissionService service;
	
	PermissionResource(PermissionService service){
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> getPermissions() {
		Map<String, Object> response = new HashMap<>();
		
		try {
			List<Permission> permissions = service.getPermissions();
	        response.put("success", true);
	        response.put("data", permissions);
	        response.put("message", "Permissões encontradas!");
	        
	        return ResponseEntity.ok(response);
		
		} catch (Exception exception) {
		
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
		}
	}
	
	@GetMapping("/{Id}")
	public ResponseEntity<Map<String, Object>> getPermission(@PathVariable String Id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			Permission permissions = service.getPermission(Id);
	        response.put("success", true);
	        response.put("data", permissions);
	        response.put("message", "Permissões encontradas!");
	        
	        return ResponseEntity.ok(response);
		
		} catch (Exception exception) {
		
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
		}
	}
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> insertPermission(@RequestBody Map<String, Object> jsonData){
		Map<String, Object> response = new HashMap<>();
		
		try {
			Permission permission = service.insert(jsonData);
			
	        response.put("success", true);
	        response.put("data", permission);
	        response.put("message", "Permissão inserida com sucesso!");
	        
	        return ResponseEntity.ok(response);
			
		} catch (DataIntegrityViolationException exception) {
			
			throw exception;
		
		} catch (Exception exception) {
			
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<Map<String, Object>> updatePermission(@RequestBody Map<String, Object> jsonData){
		Map<String, Object> response = new HashMap<>();
		
		try {
			Permission permission = service.update(jsonData);
			
	        response.put("success", true);
	        response.put("data", permission);
	        response.put("message", "Permissão atualizada com sucesso!");
	        
	        return ResponseEntity.ok(response);
			
		} catch (DataIntegrityViolationException exception) {
			
			throw exception;
		
		} catch (Exception exception) {
			
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
		}
	}

	@DeleteMapping("/delete/{Id}")
	public ResponseEntity<Map<String, Object>> deletePermission(@PathVariable String Id) {
		Map<String, Object> response = new HashMap<>();
		try {
			service.delete(Id);
	        response.put("success", true);
	        response.put("message", "Permissão deletada!");
	        
	        return ResponseEntity.ok(response);
		
		} catch (Exception exception) {
		
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
		}
	}
	
}
