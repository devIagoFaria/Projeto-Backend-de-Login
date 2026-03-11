package com.cls.erp.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contacts")
public class Contact {
	
    @Id
	@GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    
    @OneToOne
    @JoinColumn(name = "ownerId")
    @JsonBackReference
    public User owner;
	
	public String phoneNumber;
	
	public String mail;
	
    public Contact(Map<String, String> data) {
        this.phoneNumber = data.get("phoneNumber");
        this.mail = data.get("mail");
    }

	public Contact() {
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	

}
