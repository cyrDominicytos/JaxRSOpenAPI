package fr.istic.taa.jaxrs.dao.generic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityTransaction;
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
public class UserDao extends AbstractJpaDao<Long, User> {
	
	public UserDao() {
		this.setClazz(User.class);
	}

	@Override
	public Boolean canBeDeleted(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Find the list of user and their type (hint : user has Support subclass with sigle table Inheritance) 
	 * @return a list of user or an empty list
	 */
	public List<User> findAllWithRole() {
		
		return null;
	}
	
	/*@Override
	public void delete(User user) {
		super.delete(user);
		TicketDao ticketDao = new TicketDao();
		ticketDao.deleteUserData(user);
	
		
	}*/
}
