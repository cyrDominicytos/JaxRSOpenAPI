package fr.istic.taa.jaxrs.dto;

import jakarta.validation.constraints.NotNull;

public class TagCreateDto {
	@NotNull(message = "The Tag name can not be null")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
