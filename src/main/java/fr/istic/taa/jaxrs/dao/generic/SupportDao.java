package fr.istic.taa.jaxrs.dao.generic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import fr.istic.taa.jaxrs.domain.Support;
import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.domain.Ticket;
/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
public class SupportDao extends AbstractJpaDao<Long, Support> {
	
	public SupportDao() {
		this.setClazz(Support.class);
	}

	/**
	 * Find the list of available supports (support that can be assigned to a ticket). It will be a support that are not already affect to the ticket 
	 * @param id, the ticket id
	 * @return a list of support or an empty list
	 */
	public List<Support> findAvailableSupports(Long ticketId) {
		//TypedQuery<Support> query = entityManager.createQuery("Select DISTINCT s from Support as s JOIN s.assignedTickets as ticket where ticket.id != :ticketId" , Support.class);	
		TypedQuery<Support> query = entityManager.createQuery("SELECT DISTINCT s FROM Support s WHERE NOT EXISTS (SELECT t FROM s.assignedTickets t WHERE t.id = :ticketId)" , Support.class);	
		return query.setParameter("ticketId", ticketId).getResultList();		
	}
	
	
	@Override
	public Boolean canBeDeleted(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

