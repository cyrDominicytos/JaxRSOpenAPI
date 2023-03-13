package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
@Entity
public class Message implements Serializable {
 
 private Long id;
 private String content;
 private Ticket ticket;
 private Person person;
 private Boolean isSupportMessage; //this will allow to know if the person who send the message is a support or a default user
 protected LocalDateTime created_at = LocalDateTime.now();
 
 
@OneToOne
 public Ticket getTicket() {
	return ticket;
}
public void setTicket(Ticket ticket) {
	this.ticket = ticket;
}

 
@OneToOne
public Person getPerson() {
	return person;
}
public void setPerson(Person person) {
	this.person = person;
}
@Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_seq")
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
 
 public LocalDateTime getCreated_at() {
		return created_at;
}
public void setCreated_at(LocalDateTime created_at) {
	this.created_at = created_at;
}
public Boolean getIsSupportMessage() {
	return isSupportMessage;
}
public void setIsSupportMessage(Boolean isSupportMessage) {
	this.isSupportMessage = isSupportMessage;
}
 
}
