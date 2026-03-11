package com.cls.erp.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cls.erp.model.Profile;
import com.cls.erp.model.User;
import com.cls.erp.projection.UserProjection;
import com.cls.erp.service.UserService;


@RestController
@RequestMapping("/user")
public class UserResource {
	
    private final UserService userService;
    
    public UserResource(UserService userService){
    	this.userService = userService;
    }
    
	@GetMapping
	public ResponseEntity<Map<String, Object>> users() {
		Map<String, Object> response = new HashMap<>();
		
		try {
			List<User> users = userService.getUsers();
	        response.put("success", true);
	        response.put("data", users);
	        response.put("message", "Usuarios encontrados!");
	        
	        return ResponseEntity.ok(response);
		
		} catch (Exception exception) {
		
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
		}
	}

    @PutMapping("/update")
	public ResponseEntity<Map<String, Object>> updateUser(@RequestBody Map<String, Object> userJson) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			User user = userService.updateUser(userJson);
			
	        response.put("success", true);
	        response.put("user", user);
	        response.put("message", "Usuário atualizado com sucesso!");
	        
	        return ResponseEntity.ok(response);
			
		} catch (Exception exception) {
			
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
		}
	}

	@GetMapping("/{Id}")
	public ResponseEntity<Map<String, Object>> getUser(@PathVariable String Id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			UserProjection user = userService.getUserById(Id);
	        response.put("success", true);
	        response.put("data", user);
	        response.put("message", "Usuário encontrado!");
	        
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
			User deletedUser = userService.deleteUser(Id);
	        response.put("success", true);
	        response.put("data", deletedUser.getUsername());
	        response.put("message", "Usuário deletado com sucesso!");
	        
	        return ResponseEntity.ok(response);
		
		} catch (Exception exception) {
		
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
		}
	}
	
}
