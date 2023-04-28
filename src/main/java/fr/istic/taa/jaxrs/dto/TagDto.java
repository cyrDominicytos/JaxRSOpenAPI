package fr.istic.taa.jaxrs.dto;

import javax.xml.crypto.Data;

import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.services.DateFormatter;

public class TagDto {
	private Long id;
	private String name;
	private String textColor;
	private String backgroundColor;
	private String created_at; 
	
	/**
	 * Create an instance of TagDto with Tag object
	 * @param tag, the Tag object
	 */
	public TagDto(Tag tag){
		if(tag!=null) {
			this.id = tag.getId();
			this.name = tag.getName();
			this.backgroundColor = tag.getBackgroundColor();
			this.textColor = tag.getTextColor();
			this.created_at = DateFormatter.formatLocalDateTime(tag.getCreated_at());
		}else throw new IllegalArgumentException("The Tag instance can not be null");
	}
	
	public TagDto() {
		
	}
	
	public Long getId() {
		return id;
	}

	public TagDto setId(Long id) {
		this.id = id;
		return this;
	}

	public String getCreated_at() {
		return created_at;
	}

	public TagDto setCreated_at(String created_at) {
		this.created_at = created_at;
		return this;
	}


	public String getName() {
		return name;
	}

	public TagDto setName(String name) {
		this.name = name;
		return this;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
}
