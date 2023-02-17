package fr.istic.taa.jaxrs.dto;

import java.time.LocalDateTime;

import fr.istic.taa.jaxrs.domain.Bug;
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
	BugDto(BugDtoBuilder builder){
		if(builder!=null) {
			this.id = builder.id;
			this.body = builder.body;
			this.status = builder.status;
		}
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	//pattern builder
		public static class BugDtoBuilder{
			//required params
			private Long id;
			private String body;
			private int status = 0;
			
			//optional params
			private LocalDateTime created_at;
			
			public BugDtoBuilder(Bug bug) {
				if(bug!=null) {
					this.id = bug.getId();
					this.body = bug.getBody();
										
				}
			}
			
			
			public BugDtoBuilder setId(Long id) {
				this.id = id;
				return this;
			}
			
			
			public BugDtoBuilder setBody(String body) {
				this.body = body;
				return this;
			}
			
			public BugDtoBuilder setStatus(int status) {
				this.status = status;
				return this;
			}
			
			public BugDto build() {
				return new BugDto(this);
			}
		}


}
