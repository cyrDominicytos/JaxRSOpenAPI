package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
 
 @OneToOne
 public Ticket getTicket() {
	return ticket;
}
public void setTicket(Ticket ticket) {
	this.ticket = ticket;
}
private Person person;
 
@OneToOne 
public Person getPerson() {
	return person;
}
public void setPerson(Person person) {
	this.person = person;
}
@Id
 @GeneratedValue
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
 
}
