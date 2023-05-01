package fr.istic.taa.jaxrs.dao.generic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import fr.istic.taa.jaxrs.domain.Message;
import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.domain.Ticket;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
public class MessageDao extends AbstractJpaDao<Long, Message>{
	public MessageDao() {
		this.setClazz(Message.class);
	}

	@Override
	public Boolean canBeDeleted(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Find the messages that had been send on a ticket
	 * @param id, the ticket id
	 * @return a list of message or an empty list
	 */
	public List<Message> findTicketMessages(Long id) {
		TypedQuery<Message> query = entityManager.createQuery("Select m from Message as m JOIN m.ticket as t where t.id = :id" , Message.class);	
		return query.setParameter("id", id).getResultList();		
	}
}

