package fr.istic.taa.jaxrs.dto;

import java.time.LocalDateTime;

import fr.istic.taa.jaxrs.domain.Support;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
public class SupportDto extends SupportCreateDto {
	private Long id;
	private String created_at;
	
	public SupportDto(Support s) {
		super(s);
		id = s.getId();
		created_at = DateFormatter.formatLocalDateTime(s.getCreated_at());	
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}
