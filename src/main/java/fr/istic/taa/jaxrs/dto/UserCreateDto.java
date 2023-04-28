package fr.istic.taa.jaxrs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 *This class is a custome DTO to define the format of new User data model
 */
public class UserCreateDto {
	@NotBlank(message = "The User name can not be blank")
	private String name;
	
	@NotBlank(message = "The User mail adress can not be blank")
	@Email(message="The User mail adress is not valid")
	private String email;
	
	@NotBlank(message = "The User password can not be blank")
	private String password = "Test@2023";
	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
