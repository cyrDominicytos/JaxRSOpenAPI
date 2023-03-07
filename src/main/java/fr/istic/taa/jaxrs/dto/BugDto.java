package fr.istic.taa.jaxrs.dto;

import java.time.LocalDateTime;
/**
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
public class BugDto {
	private Long id;
	private String body;
	private int status = 0;
	private Long user_id;
	private String created_at;
	
	
	public Long getId() {
		return id;
	}
	public BugDto setId(Long id) {
		this.id = id;
		return this;
	}
	public String getBody() {
		return body;
	}
	public BugDto setBody(String body) {
		this.body = body;
		return this;
	}
	public int getStatus() {
		return status;
	}
	public BugDto setStatus(int status) {
		this.status = status;
		return this;
	}
	public Long getUser_id() {
		return user_id;
	}
	public BugDto setUser_id(Long user_id) {
		this.user_id = user_id;
		return this;
	}
	public String getCreated_at() {
		return created_at;
	}
	public BugDto setCreated_at(String created_at) {
		this.created_at = created_at;
		return this;
	}
	
	
}
