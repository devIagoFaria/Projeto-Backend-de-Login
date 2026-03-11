package com.cls.erp.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "addresses")
public class Address {
	

    @Id
	@GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    
    @OneToOne
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    public User owner;

    public String street;
	
	public String zipcode;
	
	public String city;
	
	public String country; 
	
	public String complement;


	public Address(Map<String, String> data) {
	    this.street = data.get("street");
	    this.zipcode = data.get("zipcode");
	    this.city = data.get("city");
	    this.country = data.get("country");
	    this.complement = data.get("complement");
	}

	public Address() {}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	
	
}
