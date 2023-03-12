package fr.istic.taa.jaxrs.dto;

/**
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
public class TicketDto {
	private Long id;
	private String content;
	private int status = 0;
	private Long user_id;
	private String created_at;
	
	
	public Long getId() {
		return id;
	}
	public TicketDto setId(Long id) {
		this.id = id;
		return this;
	}
	
	public String getContent() {
		return content;
	}
	public TicketDto setContent(String content) {
		this.content = content;
		return this;
	}
	public int getStatus() {
		return status;
	}
	public TicketDto setStatus(int status) {
		this.status = status;
		return this;
	}
	public Long getUser_id() {
		return user_id;
	}
	public TicketDto setUser_id(Long user_id) {
		this.user_id = user_id;
		return this;
	}
	public String getCreated_at() {
		return created_at;
	}
	public TicketDto setCreated_at(String created_at) {
		this.created_at = created_at;
		return this;
	}
	
	
}
