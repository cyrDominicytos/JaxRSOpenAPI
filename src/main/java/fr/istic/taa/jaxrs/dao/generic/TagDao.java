package fr.istic.taa.jaxrs.dao.generic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.domain.Ticket;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
public class TagDao extends AbstractJpaDao<Long, Tag>{
	public TagDao() {
		this.setClazz(Tag.class);
	}

	@Override
	public Boolean canBeDeleted(Long id) {
		TicketDao ticketDao = new TicketDao();
		return !ticketDao.hasLinkedWithTag(id);
	}
	
	
	/**
	 * Find in a given tag id list, the ids that are not already assign to a ticket 
	 * @param ids, the given tag ids
	 * @return a list of ticket or an empty list
	 */
	public List<Tag> getUnlinkedTagWithTicket(List<Long> ids, Long idTicket) {
		TypedQuery<Tag> query = entityManager.createQuery("Select t from Tag t where t.id IN :tagIds and t.id NOT IN (SELECT ticket_tag.id FROM Ticket ticket JOIN ticket.tags as ticket_tag where ticket.id= :idTicket)" , Tag.class);	
		return query.setParameter("tagIds", ids).setParameter("idTicket", idTicket).getResultList();		
	}
	
	
}
