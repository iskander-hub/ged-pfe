package org.ged.service;

import org.ged.entities.AppRole;
import org.ged.entities.AppUser;

public interface AccountService {
   
	public AppUser saveUser(AppUser user);
	public AppRole saveRole(AppRole role);
	public void addRoleToUser(String username,String roleName);
	public AppUser findUserByUsername(String username);
}
