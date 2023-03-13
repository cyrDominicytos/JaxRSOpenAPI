package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
@Entity
@SequenceGenerator(name="user_seq", sequenceName="user_sequence", allocationSize=1)
public class User extends Person implements Serializable{
	
	private List<Ticket> tickets;
	

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL) //when 
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
}
