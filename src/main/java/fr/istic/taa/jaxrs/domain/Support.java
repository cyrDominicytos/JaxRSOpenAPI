package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;

/**
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 */
@Entity
@SequenceGenerator(name="support_seq", sequenceName="support_sequence", allocationSize=1)
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
	
	//Delete the relationships associated with a Support before deleting the support
	@PreRemove
	public void removeTicketsFromSupport() {
	    for (Ticket ticket : tickets) {
	        ticket.getSupports().remove(this);
	    }
	    tickets.clear();
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}
}
