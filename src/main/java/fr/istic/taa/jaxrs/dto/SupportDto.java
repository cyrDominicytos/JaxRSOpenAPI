package fr.istic.taa.jaxrs.dto;

import java.time.LocalDateTime;

import fr.istic.taa.jaxrs.domain.Support;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
public class SupportDto {
	private Long id;
	private String name;
	private String email;
	private String grad = "DEVELOPER";
	private LocalDateTime created_at;
	
	public 	SupportDto() {
		
	}
	
	public SupportDto(Long id, String name, String email, String grad) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.grad = grad;
	}
	
	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	
	private SupportDto(SupportDtoBuilder builder) {
		this.id = builder.id;
		this.email = builder.email;
		this.name = builder.name;
		this.grad = builder.grad;
		this.created_at = builder.created_at;
	}
	
	
	//pattern builder
	public static class SupportDtoBuilder{
		//required params
		private Long id;
		private String name;
		private String email;
		private String grad;
		
		//optional params
		private LocalDateTime created_at;
		
		public SupportDtoBuilder(Support support) {
			if(support!=null) {
				this.id = support.getId();
				this.name = support.getName();
				this.email = support.getEmail();
				this.grad = support.getGrad();
				this.created_at = support.getCreated_at();
			}
		}
		
		public SupportDtoBuilder setId(Long id) {
			this.id = id;
			return this;
		}
		
		public SupportDtoBuilder setName(String name) {
			this.name = name;
			return this;
		}
		
		public SupportDtoBuilder setGrad(String grad) {
			this.grad = grad;
			return this;
		}
		
		public SupportDtoBuilder setEmail(String email) {
			this.email = email;
			return this;
		}
		
		public SupportDtoBuilder setCreated_at(LocalDateTime created_at) {
			this.created_at = created_at;
			return this;
		}
		
		public SupportDto build() {
			return new SupportDto(this);
		}
	}

}
