package com.cls.erp.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleUniqueConstraint() {
    	Map<String, Object> response = new HashMap<>();
    	
    	response.put("message", "DeveloperName já existe!");
		response.put("success", false);
    	
        return ResponseEntity
                .badRequest()
                .body(response);
    }
}
