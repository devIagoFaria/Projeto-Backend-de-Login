package com.cls.erp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cls.erp.exception.DuplicateUsernameException;
import com.cls.erp.exception.InvalidCredentialsException;
import com.cls.erp.model.Address;
import com.cls.erp.model.Contact;
import com.cls.erp.model.Profile;
import com.cls.erp.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class AuthService {
	
	private static final long EXPIRATION_TIME_MS = 2 * 60 * 60 * 1000;
	
	private String secret = "chaveSuperSecretaParaJwtDaClsErp123456789";
	
	private SecretKey SECRET_KEY;
	
	public static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	
    private final UserService userService;
    private final ProfileService profileService;
    
    public AuthService(UserService userService, ProfileService profileService) {
        this.userService = userService;
        this.profileService = profileService;
    }
    
    @PostConstruct
    public void init() {
        this.SECRET_KEY = Keys.hmacShaKeyFor(secret.getBytes());
    }
	    
	public String getToken(Map<String, String> userJson) throws InvalidCredentialsException {
		
		User userToAuth = userService.getUserByParameter(userJson.get("username"));
				
		if (userToAuth == null) throw new InvalidCredentialsException("User doesn't exist.");
		
		if (!encoder.matches(userJson.get("password"), userToAuth.getPassword())) throw new InvalidCredentialsException("Incorrect Password.");
		
	    Profile profile = userToAuth.getProfile();
	    
	    List<String> permissionsList = new ArrayList<String>();

	     profile.getPermissions().forEach(p -> {
	    	 String developerName = p.getDeveloperName();
	    	 permissionsList.add(developerName);
	    	 
	    	 if(p.canRead) permissionsList.add(developerName + "_R");
	    	 if(p.canInsert) permissionsList.add(developerName + "_I");
	    	 if(p.canEdit) permissionsList.add(developerName + "_U");
	    	 if(p.canDelete) permissionsList.add(developerName + "_D");
	     });
	     
		
		JwtBuilder jwt = Jwts.builder();
		jwt.setSubject(userToAuth.getId());
	    jwt.claim("username", userToAuth.getUsername());
	    jwt.claim("firstName", userToAuth.getFirstName());
	    jwt.claim("lastName", userToAuth.getLastName());
	    jwt.claim("profile", profile.getDeveloperName());
	    jwt.claim("permissions", permissionsList);
		jwt.setIssuedAt(new Date());
		jwt.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS));
		jwt.signWith(SECRET_KEY, SignatureAlgorithm.HS256);
		
		String token = jwt.compact();
		
		
		return token;
	}
	
	public User registerUser(Map<String, String> userJson) throws DuplicateUsernameException {
		
		Profile guestProfile = profileService.getProfileByParameter("guest");
		
		User userToInsert = new User();
		userToInsert.setFirstName(userJson.get("firstName"));
		userToInsert.setLastName(userJson.get("lastName"));
		userToInsert.setUsername(userJson.get("username"));
		userToInsert.setProfile(guestProfile);
		String password = AuthService.encoder.encode(userJson.get("password"));
		userToInsert.setPassword(password);

		Contact userContact = new Contact();
		userContact.setOwner(userToInsert);
		userContact.setPhoneNumber(userJson.get("phoneNumber"));
		userContact.setMail(userJson.get("mail"));
		userToInsert.setContact(userContact);
		
		Address userAddress = new Address();
		
		userAddress.setOwner(userToInsert);
		userAddress.setCity(userJson.get("city"));
		userAddress.setCountry(userJson.get("country"));
		userAddress.setStreet(userJson.get("street"));
		userAddress.setZipcode(userJson.get("zipcode"));
		userAddress.setComplement(userJson.get("complement"));
		userToInsert.setAddress(userAddress);
		
		if(userService.getUserByParameter(userJson.get("username")) != null) throw new DuplicateUsernameException("The username already exists!");
		
		return userService.insertUser(userToInsert);
	}

	public Claims validateToken(String token) {
	    try {

	        if (token.startsWith("Bearer ")) {
	            token = token.substring(7);
	        }

	        return Jwts.parserBuilder()
	                .setSigningKey(SECRET_KEY)
	                .build()
	                .parseClaimsJws(token)
	                .getBody();

	    } catch (JwtException | IllegalArgumentException e) {
	        return null;
	    }
	}
}
