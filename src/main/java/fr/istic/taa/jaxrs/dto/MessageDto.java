package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Message;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.services.DateFormatter;

public class MessageDto {
	private Long id;
	 private String content;
	 private Long ticketId;
	 private UserDto user;
	 private String created_at;
	 
	 public MessageDto(Message m) {
		 if(m==null)
			 throw new IllegalArgumentException("The message instance can not be null");
		 this.id = m.getId();
		 this.content = m.getContent();
		 this.created_at = DateFormatter.formatLocalDateTime(m.getCreated_at());;
		 this.user = new UserDto(m.getUser());
		 this.ticketId = m.getTicket().getId();
	 }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	 
	 
}
