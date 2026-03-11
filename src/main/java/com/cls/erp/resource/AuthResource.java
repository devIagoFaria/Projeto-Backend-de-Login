package com.cls.erp.resource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cls.erp.exception.InvalidCredentialsException;
import com.cls.erp.model.User;
import com.cls.erp.service.AuthService;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/auth")
public class AuthResource {
	
	private final AuthService authService;
	
    public AuthResource(AuthService authService) {
        this.authService = authService;
    }
    
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			String token = authService.getToken(credentials);
	        response.put("success", true);
	        response.put("access_token", token);
	        response.put("message", "Login realizado com sucesso!");
	        
	        return ResponseEntity.ok(response);
			
		} catch (InvalidCredentialsException exception) {
			
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
			
		} catch (Exception exception) {
			
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> data) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			User user = authService.registerUser(data);
			
			Map<String, Object> userMap = Map.of(
				    "id", user.getId(),
				    "firstName", user.getFirstName(),
				    "lastName", user.getLastName(),
				    "username", user.getUsername()
					);
			
	        response.put("success", true);
	        response.put("user", userMap);
	        response.put("message", "Usuário cadastrado com sucesso!");
	        
	        return ResponseEntity.ok(response);
			
		} catch (Exception exception) {
			
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
		}
	}
	
	@PostMapping("/validate")
	public ResponseEntity<Map<String, Object>> validate(@RequestBody Map<String, String> credentials) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			Claims isValidatedToken = authService.validateToken(credentials.get("access_token"));
	        response.put("success", true);
	        response.put("is_validate", isValidatedToken != null ? true : false);
	        response.put("user", isValidatedToken);
	        response.put("message", "Token validado com sucesso!");
	        
	        return ResponseEntity.ok(response);
			
		} catch (InvalidCredentialsException exception) {
			
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
			
		} catch (Exception exception) {
			
			response.put("message", exception.getMessage());
			response.put("success", false);
			
			return ResponseEntity.status(401).body(response);
		}
	}
	
}
