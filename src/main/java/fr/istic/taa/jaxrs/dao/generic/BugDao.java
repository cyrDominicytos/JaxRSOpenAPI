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
public class BugDao extends AbstractJpaDao<Long, Bug>{
	public BugDao() {
		this.setClazz(Bug.class);
	}
	
	/**
	 * Find the list of ticket that has the that with this id 
	 * @param id, the given tag id
	 * @return a list of ticket or an empty list
	 */
	public List<Bug> findTicketsByTag(Long id) {
		TagDao tagDao = new TagDao();
		Tag tag = tagDao.findOne(id);
		if(tag==null)
			return new ArrayList<>(); 
		TypedQuery<Bug> query = entityManager.createQuery("Select b from Bug as b JOIN b.tags as tag where tag.id = :tagId" , Bug.class);	
		return query.setParameter("tagId", id).getResultList();		
	}
	
	/**
	 * Delete all bug tickets that has been created by the related user 
	 * @param user, the related user 
	 */
	public void deleteUserData(User user) {
		TypedQuery<Bug> query = entityManager.createQuery("Delete from Bug as b where b.user = :user" , Bug.class);	
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
