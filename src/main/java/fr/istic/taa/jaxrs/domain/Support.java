package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;

/**
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 */
@Entity
public class Support extends User  implements Serializable{
	private String grad; 
	private List<Ticket> assignedTickets = new ArrayList<>();
	
	@ManyToMany(mappedBy="assignedSupport")
	public List<Ticket> getAssignedTickets() {
		return assignedTickets;
	}

	public void setAssignedTickets(List<Ticket> assignedTickets) {
		this.assignedTickets = assignedTickets;
	}

	
	//Delete the relationships associated with a Support before deleting the support
	@PreRemove
	public void removeTicketsFromSupport() {
	    for (Ticket ticket : assignedTickets) {
	        ticket.getAssignedSupport().remove(this);
	    }
	    assignedTickets.clear();
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}
}
