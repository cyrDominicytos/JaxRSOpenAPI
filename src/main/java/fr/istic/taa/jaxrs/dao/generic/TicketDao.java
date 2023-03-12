package fr.istic.taa.jaxrs.dao.generic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import fr.istic.taa.jaxrs.domain.Bug;
import fr.istic.taa.jaxrs.domain.Tag;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
public class TicketDao extends AbstractJpaDao<Long, Ticket>{
	public TicketDao() {
		this.setClazz(Ticket.class);
	}
	
	/**
	 * Find the list of ticket that has the that with this id 
	 * @param id, the given tag id
	 * @return a list of ticket or an empty list
	 */
	public List<Ticket> findTicketsByTag(Long id) {
		TagDao tagDao = new TagDao();
		Tag tag = tagDao.findOne(id);
		if(tag==null)
			return new ArrayList<>(); 
		TypedQuery<Ticket> query = entityManager.createQuery("Select b from Ticket as b JOIN b.tags as tag where tag.id = :tagId" , Ticket.class);	
		return query.setParameter("tagId", id).getResultList();		
	}
	
	/**
	 * Delete all tickets that has been created by the related user 
	 * @param user, the related user 
	 */
	public void deleteUserData(User user) {
		TypedQuery<Ticket> query = entityManager.createQuery("Delete from Ticket as b where b.user = :user" , Ticket.class);	
		 query.setParameter("user", user).executeUpdate();		
	}
	
	
	
	public Boolean hasLinkedWithTag(Long id) {
		return (findTicketsByTag(id).size()>0) ? true : false;
	}

	@Override
	public Boolean canBeDeleted(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
