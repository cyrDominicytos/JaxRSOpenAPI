package fr.istic.taa.jaxrs.dto;

import java.util.List;

import fr.istic.taa.jaxrs.domain.Tag;
import jakarta.validation.constraints.NotNull;

/**
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
public  class BugCreateDto {
	
	@NotNull(message = "The bug description can not be null")
	private String body;
	@NotNull(message = "The user id can not be null")
	private Long user_id;
	
	@NotNull(message = "You have to add at one or more tags to your bug")
	protected List<Long> tagsId;
	
	public List<Long> getTagsId() {
		return tagsId;
	}
	public void setTagsId(List<Long> tagsId) {
		this.tagsId = tagsId;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
}
