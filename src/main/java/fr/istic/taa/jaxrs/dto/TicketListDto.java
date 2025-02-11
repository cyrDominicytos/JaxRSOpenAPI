package fr.istic.taa.jaxrs.dto;

import java.util.ArrayList;
import java.util.List;

import fr.istic.taa.jaxrs.domain.Support;
import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.services.DateFormatter;
import fr.istic.taa.jaxrs.services.State;

public class TicketListDto {
	
	private Long id;
	private String title;
	private String content;
	private State state;
	private UserDto user;
	private String created_at;
	private List<TagDto> tags = new ArrayList<>();
	private List<SupportDto> assignedSupports = new ArrayList<>();

	public TicketListDto(Ticket t) {
		if(t==null) throw new IllegalArgumentException("The ticket instance can not be null");
		else {
			this.id = t.getId();
			this.content = t.getContent();
			this.user = new UserDto(t.getUser());
			this.state = t.getState();
			this.title = t.getTitle();
			this.created_at = DateFormatter.formatLocalDateTime(t.getCreated_at());
			
			//add tags
			if(!t.getTags().isEmpty())
			{
				for(Tag tag: t.getTags()) {
					tags.add(new TagDto(tag));
				}
			}
										
			//add supports
			System.out.println("Taille "+t.getAssignedSupport().size());
			if(t.getAssignedSupport().size() > 0)
			{
				for(Support s: t.getAssignedSupport()) {
					assignedSupports.add(new SupportDto(s));
				}
			}
		}
		
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
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<TagDto> getTags() {
		return tags;
	}
	public void setTags(List<TagDto> tags) {
		this.tags = tags;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<SupportDto> getAssignedSupports() {
		return assignedSupports;
	}

	public void setAssignedSupports(List<SupportDto> assignedSupports) {
		this.assignedSupports = assignedSupports;
	}	
	
	

}
