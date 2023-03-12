package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
@Entity
public class Ticket implements Serializable {

	protected Long id;
	protected String content;
	private String state;
	protected User user; 
	protected List<Tag> tags =  new ArrayList<>();
	protected List<Support> supports =  new ArrayList<>();
	protected List<Message> messages = new ArrayList<>();
	protected LocalDateTime created_at = LocalDateTime.now();
	
	@OneToMany(mappedBy="ticket")
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	
	@ManyToMany
	public List<Support> getSupports() {
		return supports;
	}
	public void setSupports(List<Support> supports) {
		this.supports = supports;
	}
	
	@ManyToMany
	@JoinTable(
		name="ticket_tag",
		joinColumns=@JoinColumn(name="tag_id"),
		inverseJoinColumns=@JoinColumn(name="ticket_id")
	)
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
		
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
