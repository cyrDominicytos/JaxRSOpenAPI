package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Message;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.services.DateFormatter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MessageCreateDto {
	 @NotBlank(message = "The message content can not be blank")
	 private String content;
	 @NotNull(message = "The ticket id can not be null")
	 private Long ticketId;
	 
	 @NotNull(message = "The user id can not be null")
	 private Long userId;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	 
		 
	 
}
