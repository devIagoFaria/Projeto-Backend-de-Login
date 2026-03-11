package com.cls.erp.projection;

import java.util.Set;

import com.cls.erp.model.Permission;

public interface ProfileProjection {
	String getId();
	String getDeveloperName();
	Set<Permission> getPermissions();
}
