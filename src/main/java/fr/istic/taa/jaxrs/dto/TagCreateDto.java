package fr.istic.taa.jaxrs.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 *This class is a custome DTO to define the format to create new Tag
 */
public class TagCreateDto {
	@NotBlank(message = "The Tag name can not be blank")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
