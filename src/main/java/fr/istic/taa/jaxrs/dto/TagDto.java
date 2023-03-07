package fr.istic.taa.jaxrs.dto;

import javax.xml.crypto.Data;

import fr.istic.taa.jaxrs.domain.Tag;

public class TagDto {
	private Long id;
	private String name;
	private String created_at; 
	
	/**
	 * Create an instance of TagDto with Tag object
	 * @param tag, the Tag object
	 */
	public TagDto(Tag tag){
		if(tag!=null) {
			this.id = tag.getId();
			this.name = tag.getName();
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
	
	
}
