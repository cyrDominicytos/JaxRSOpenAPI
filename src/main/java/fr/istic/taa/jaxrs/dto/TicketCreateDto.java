package fr.istic.taa.jaxrs.dto;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import fr.istic.taa.jaxrs.domain.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
public  class TicketCreateDto {
	
	@NotBlank(message = "The bug description can not be blank")
	private String content;
	@NotNull(message = "The user id can not be blank")
	private Long user_id;
	
	@NotNull(message = "The ticket tags id can not be null")
	@NotEmpty(message = "You have to add one or many tags to your ticket")
	protected List<Long> tagsId;
	
	public List<Long> getTagsId() {
		return tagsId;
	}
	public void setTagsId(List<Long> tagsId) {
		this.tagsId = tagsId;
	}
		
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
}
