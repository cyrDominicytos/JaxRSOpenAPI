package fr.istic.taa.jaxrs.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 *This class is a custome DTO to define the format of new User data model
 */
public class UserCreateDto {
	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	//@NotNull(message="The User name can not be null", messageKey = "password.length")
	private String name;
	@NotNull(message="{javax.validation.constraints.NotNull.message}")
	@Email(message="{javax.validation.constraints.Email.message}")
	private String email;
	
	public String getName() {
		return name;
	}
	public UserCreateDto setName(String name) {
		this.name = name;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public UserCreateDto setEmail(String email) {
		this.email = email;
		return this;
	}
}
