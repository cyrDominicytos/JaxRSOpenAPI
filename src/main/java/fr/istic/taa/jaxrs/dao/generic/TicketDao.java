package fr.istic.taa.jaxrs.dao.generic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
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
		TypedQuery<Ticket> query = entityManager.createQuery("Select t from Ticket as t JOIN t.tags as tag where tag.id = :tagId" , Ticket.class);	
		return query.setParameter("tagId", id).getResultList();		
	}
	
	/**
	 * Find the list of ticket that has been created by a user
	 * @param id, the user id
	 * @return a list of ticket or an empty list
	 */
	public List<Ticket> findTicketsByUser(Long id) {
		TypedQuery<Ticket> query = entityManager.createQuery("Select t from Ticket as t JOIN t.user as user  where user.id = :userId" , Ticket.class);	
		return query.setParameter("userId", id).getResultList();		
	}
	
	
	
	/**
	 * Delete all tickets that has been created by the related user 
	 * @param user, the related user 
	 */
	public void deleteUserData(User user) {
		TypedQuery<Ticket> query = entityManager.createQuery("Delete from Ticket as t where t.user = :user" , Ticket.class);	
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
