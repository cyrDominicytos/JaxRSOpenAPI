package fr.istic.taa.jaxrs.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 *This class is a custome DTO to validate user data format during login 
 */
public class UserConnectDto {
	@NotBlank(message = "The User mail adress can not be blank")
	@Email(message="The User mail address is not valid")
	private String email;
	
	@NotBlank(message = "The User password can not be blank")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
