package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 */
@Entity
public class Support extends Person  implements Serializable{
	private String grad;
	private List<Ticket> tickets;
	
	@ManyToMany(mappedBy="supports")
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}
}
