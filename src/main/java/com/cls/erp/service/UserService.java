package com.cls.erp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cls.erp.model.User;
import com.cls.erp.projection.UserProjection;
import com.cls.erp.repository.UserRepository;

@Service
public class UserService {
	
    @Autowired
    private UserRepository userRepo;
    
    public List<User> getUsers(){
    	return userRepo.findAll();
    }

	public User getUserByParameter(String parameter) {
		if(isUUID(parameter)) {
			return userRepo.findById(parameter).orElse(null);
		} else {
			return userRepo.findByUsername(parameter);			
		}
	}
	
	public UserProjection getUserById(String Id) {
		return userRepo.findProjectById(Id);
	}
	
	public User updateUser(Map<String, Object> data) throws Exception {
		
		User userToUpdate = userRepo.findById((String) data.get("Id")).orElse(null);
		userToUpdate.setFirstName((String) data.get("firstName"));
		userToUpdate.setLastName((String) data.get("lastName"));
		
		userToUpdate.getAddress().setCity((String) data.get("city"));
		userToUpdate.getAddress().setComplement((String) data.get("complement"));
		userToUpdate.getAddress().setCountry((String) data.get("country"));
		userToUpdate.getAddress().setStreet((String) data.get("street"));
		userToUpdate.getAddress().setZipcode((String) data.get("zipcode"));

		userToUpdate.getContact().setMail((String) data.get("mail"));
		userToUpdate.getContact().setPhoneNumber((String) data.get("phoneNumber"));
		
		return userRepo.save(userToUpdate);
	}
	
    public User insertUser(User user) {
        return userRepo.save(user);
    }
    
    public User deleteUser(String id) throws Exception {
    	
        if (!userRepo.existsById(id)) throw new Exception("User not found!");
        
        User deletedUser = userRepo.findById(id).orElse(null);

        userRepo.deleteById(id);
        
    	return deletedUser;
    }
    
    private boolean isUUID(String value) {
    	String UUID_REGEX = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}$";
        return value != null && value.matches(UUID_REGEX);
    }
}
