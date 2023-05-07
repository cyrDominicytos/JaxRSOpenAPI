package fr.istic.taa.jaxrs.dto;

import java.io.Serializable;

import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.services.DateFormatter;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
public class UserDto implements Serializable {
	private Long id;
	private String name;
	private String email;
	private String role;
	private String created_at;
	
	public 	UserDto() {
		
	}
	
	public 	UserDto(User user) {
		if(user==null) throw new  IllegalArgumentException("The user instance can not be null");
		else {
			this.id = user.getId();
			this.name = user.getName();
			this.email = user.getEmail();
			this.role = user.getRole();
			this.created_at = DateFormatter.formatLocalDateTime(user.getCreated_at());
		}
	}
	
	public UserDto(Long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
		
}
