package com.cls.erp.model.pdv;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pdv_credit_payment_option")
public class PDVCreditPaymentOption {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String description;
	
	private Double minValue;

	public PDVCreditPaymentOption() {}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

}
