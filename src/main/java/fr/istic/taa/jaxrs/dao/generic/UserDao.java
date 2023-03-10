package fr.istic.taa.jaxrs.dao.generic;

import javax.persistence.EntityTransaction;

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
	
	@Override
	public void delete(User user) {
		super.delete(user);
		BugDao bugDao = new BugDao();
		bugDao.deleteUserData(user);
		
		FeatureDao featureDao = new FeatureDao();
		featureDao.deleteUserData(user);
		
		

	}
}
