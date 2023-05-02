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
	
	/**
	 * Find user by its email and password
	 * @param email, the user email
	 * @param pwd, the user password
	 * @return an instance of User or null
	 */
	public User findByEmailAndPassword(String email, String pwd) {
		TypedQuery<User> query = entityManager.createQuery("Select u from User as u where u.email = :email and u.password = :pwd" , User.class);	
		return query.setParameter("email", email).setParameter("pwd", pwd).getSingleResult();	
	}
	

	@Override
	public Boolean canBeDeleted(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	/*@Override
	public void delete(User user) {
		super.delete(user);
		TicketDao ticketDao = new TicketDao();
		ticketDao.deleteUserData(user);
	
		
	}*/
}
