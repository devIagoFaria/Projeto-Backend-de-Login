package com.cls.erp.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	public String Id;

	public String FirstName;
	
	public String LastName;

	@Column(nullable = false)
	public Boolean isActive = false;
	
	@Column(unique = true, nullable = false)
	public String username;
	
	public String password;
	
	@OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
	@JsonManagedReference
	public Contact contact;
	
	@OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
	@JsonManagedReference
	public Address address;
	
	@ManyToOne
	@JoinColumn(name="profile_id", nullable=false)
	public Profile profile;
	
	public User() {	}

	
	public User(String firstName, Contact contact, Address address) {
		this.Id = "0";
	}
	
	public User(String firstName, 
			String lastName, String username, 
			String password, Contact contact, 
			Address address, Profile profile) {
		this.Id = UUID.randomUUID().toString(); 
		FirstName = firstName;
		LastName = lastName;
		this.username = username;
		this.password = password;
		this.contact = contact;
		this.address = address;
		this.profile = profile;
	}
	
	public User(Profile profile) {
		this.profile = profile;
	}
	
	public User(String firstName, String lastName, String username, String password) {
		this.Id = UUID.randomUUID().toString(); 
		FirstName = firstName;
		LastName = lastName;
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return this.FirstName + ' ' + this.LastName;
	}

	public String getId() {
		return Id;
	}
	
	public String getPassword() {
		return password;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}


	public String getLastName() {
		return LastName;
	}


	public void setLastName(String lastName) {
		LastName = lastName;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setId(String id) {
		Id = id;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Profile getProfile() {
		return profile;
	}


	public void setProfile(Profile profile) {
		this.profile = profile;
	}


	public Boolean getIsActive() {
		return isActive;
	}


	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
	
}
