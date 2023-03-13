package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Support;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 * 
 * This class is a custom DTO for support creation 
 */
public class SupportCreateDto {
	
	@NotBlank(message = "The Support name can not be blank")
	private String name;
	
	@NotBlank(message = "The Support email can not be blank")
	@Email(message="The Support email is not valid")
	private String email;
	
	@NotBlank(message = "The Support grad can not be blank")
	private String grad;
	
	public SupportCreateDto() {
       
    }
	public SupportCreateDto(String name, String email, String grad) {
        this.name = name;
        this.email = email;
        this.grad = grad;
    }
	
	public  SupportCreateDto(Support support) {
		if(support!=null) {
			this.email = support.getEmail();
			this.name  = support.getName();
			this.grad = support.getGrad();
		}else throw new IllegalArgumentException("The support instance can not be null");
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


	public String getGrad() {
		return grad;
	}


	public void setGrad(String grad) {
		this.grad = grad;
	}
		
}