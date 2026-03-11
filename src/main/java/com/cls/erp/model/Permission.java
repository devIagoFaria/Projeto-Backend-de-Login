package com.cls.erp.model;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Permission {

    @Id
	@GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
	
	public String name;
	
	public String description;
	
	@Column(unique = true)
	public String developerName;
	
	public Boolean canInsert = false;

	public Boolean canEdit = false;
	
	public Boolean canDelete = false;
	
	public Boolean canRead = false;
	
	private OffsetDateTime createdAt = OffsetDateTime.now();
	
	private OffsetDateTime updatedAt;
	
    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    private Set<Profile> profiles = new HashSet<>();

	public Permission() {}

	public Permission(String name, String description, String developerName) {
		this.name = name;
		this.description = description;
		this.developerName = developerName;
	}

	public void setProfiles(Set<Profile> profiles) {
		this.profiles = profiles;
	}
	
	public Set<Profile> getProfiles() {
	    return profiles;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public Boolean isCanInsert() {
		return canInsert;
	}

	public void setCanInsert(Boolean isCanInsert) {
		this.canInsert = isCanInsert;
	}

	public Boolean isCanEdit() {
		return canEdit;
	}

	public void setCanEdit(Boolean isCanEdit) {
		this.canEdit = isCanEdit;
	}

	public Boolean isCanDelete() {
		return canDelete;
	}

	public void setCanDelete(Boolean isCanDelete) {
		this.canDelete = isCanDelete;
	}

	public Boolean isCanRead() {
		return canRead;
	}

	public void setCanRead(Boolean isCanRead) {
		this.canRead = isCanRead;
	}
	

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt() {
		this.createdAt = OffsetDateTime.now();
	}

	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
