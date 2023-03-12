package fr.istic.taa.jaxrs.dto;

import java.time.LocalDateTime;

import fr.istic.taa.jaxrs.domain.Support;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
public class SupportDto {
	private Long id;
	@NotBlank(message = "The Support name can not be blank")
	private String name;
	
	@NotBlank(message = "The Support email can not be blank")
	@Email(message="The Support email is not valid")
	private String email;
	
	@NotBlank(message = "The Support grad can not be blank")
	private String grad;
	private String created_at;
	
	public SupportDto(Support support) {
		if(support!=null) {
			this.email = support.getEmail();
			this.name  = support.getName();
			this.grad = support.getGrad();
			this.id = support.getId();
			this.created_at = DateFormatter.formatLocalDateTime(support.getCreated_at());	
		}else throw new IllegalArgumentException("The support instance can not be null");
		
		
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


	public String getGrad() {
		return grad;
	}


	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}
