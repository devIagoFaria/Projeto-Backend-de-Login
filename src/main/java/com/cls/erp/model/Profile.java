package com.cls.erp.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Profile {
	
    @Id
	@GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
	
	public String name;
	
	public String description;
	
	@Column(unique = true)
	public String developerName;
	
    @ManyToMany
    @JoinTable(
        name = "profile_permission",
        joinColumns = @JoinColumn(name = "profile_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions = new HashSet<>();

	public Profile() {}
	
	public Profile( String name, String description, String developerName) {
		this.name = name;
		this.description = description;
		this.developerName = developerName;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	
	public void addPermission(Permission permission) {
	    this.permissions.add(permission);
	    permission.getProfiles().add(this);
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public String getId() {
		return Id;
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

}
