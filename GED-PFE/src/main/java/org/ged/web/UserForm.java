package org.ged.web;

import org.ged.entities.AppRole;

public class UserForm {
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	private Long id;
	private String username;
	private String password;
	private AppRole role;
	public AppRole getRole() {
		return role;
	}
	public void setRole(AppRole role) {
		this.role = role;
	}

	
	
}
