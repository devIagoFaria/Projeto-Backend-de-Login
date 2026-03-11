package com.cls.erp.model.pdv;

import com.cls.erp.model.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pdv")
public class PDV {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	private Boolean credit;
	
	private Boolean debit;

	private Boolean cash;
	
	private Boolean pix;
	
    @OneToOne
    @JoinColumn(name = "owner_id")
    @JsonBackReference
    public User owner;
	
	public PDV () {}

	public Boolean getCredit() {
		return credit;
	}

	public void setCredit(Boolean credit) {
		this.credit = credit;
	}

	public Boolean getDebit() {
		return debit;
	}

	public void setDebit(Boolean debit) {
		this.debit = debit;
	}

	public Boolean getCash() {
		return cash;
	}

	public void setCash(Boolean cash) {
		this.cash = cash;
	}

	public Boolean getPix() {
		return pix;
	}

	public void setPix(Boolean pix) {
		this.pix = pix;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getId() {
		return id;
	}
	
	

	
}
