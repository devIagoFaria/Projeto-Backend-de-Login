package com.cls.erp.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cls.erp.model.Address;
import com.cls.erp.model.Contact;
import com.cls.erp.model.Permission;
import com.cls.erp.model.Profile;
import com.cls.erp.model.User;
import com.cls.erp.repository.PermissionRepository;
import com.cls.erp.repository.ProfileRepository;
import com.cls.erp.repository.UserRepository;
import com.cls.erp.service.AuthService;

@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner initData(ProfileRepository profileRepo, UserRepository userRepo, PermissionRepository permissionRepo) {
		return args -> {
			
			Profile adminProfile = profileRepo.findByDeveloperName("admin");

			if(adminProfile == null) {
				adminProfile = new Profile("Administrator","Usuario com todos os acessos e permissões","admin");
				profileRepo.save(adminProfile);
			}
			
			Profile guestProfile = profileRepo.findByDeveloperName("guest");

			if(guestProfile == null) {
				guestProfile = new Profile("Guest","Usuário padrão","guest");
				profileRepo.save(guestProfile);
			}

            User admin = userRepo.findByUsername("oiagofaria");
            
            if(admin == null) {
            	
            	admin = new User();
            	
        		Map<String, String> mapCtt1 = new HashMap<>();
        		mapCtt1.put("phoneNumber", "+55 (21) 9967-4301");
        		mapCtt1.put("mail", "ifs36098@gmail.com");
        		Contact ctt1 = new Contact(mapCtt1);
        		ctt1.setOwner(admin);
        		
        		Map<String, String> mapAddr1 = new HashMap<>();
        		mapAddr1.put("street", "16 Travessa Francisco Freire");
        		mapAddr1.put("zipcode", "21610340");
        		mapAddr1.put("city", "Rio de Janeiro");
        		mapAddr1.put("country", "Brazil");
        		mapAddr1.put("complement", "Casa 6, Apt 201");
        		Address addr1 = new Address(mapAddr1);
        		addr1.setOwner(admin);
            	
	            admin.setFirstName("Iago");
	            admin.setLastName("Faria");
	            admin.setUsername("oiagofaria");
	            admin.setProfile(adminProfile);
	            admin.setAddress(addr1);
	            admin.setContact(ctt1);
	    		String password = AuthService.encoder.encode("123");
	    		admin.setPassword(password);
	    		admin.setIsActive(true);
	    		userRepo.save(admin);
	    		
            }
            
            Permission permission = permissionRepo.findByDeveloperName("EDIT_USER");
            
            if(permission == null) {
            	permission = new Permission();
            	permission.setName("Edit User");
            	permission.setDeveloperName("EDIT_USER");
            	permission.setDescription("Allow to edit an user");
            	permission.setCanEdit(true);
            	
            	permissionRepo.save(permission);
            }
            
		};
		
	}
	
	
}
