package com.cls.erp.model.pdv;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pdv_service")
public class PDVService {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
    @OneToOne
    @JoinColumn(name = "pdv_id")
    @JsonBackReference
    public PDV pdvId;
	
	private String code;
	
	private String name;
	
	private Double price; 

	public PDVService() {}

	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
