package fr.istic.taa.jaxrs.dao.generic;

import javax.persistence.TypedQuery;

import fr.istic.taa.jaxrs.domain.Bug;
import fr.istic.taa.jaxrs.domain.Feature;
import fr.istic.taa.jaxrs.domain.User;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
public class FeatureDao  extends AbstractJpaDao<Long, Feature>{
	public FeatureDao() {
		this.setClazz(Feature.class);
	}

	
	
	/**
	 * Delete all features tickets that has been created by the related user 
	 * @param user, the related user 
	 */
	public void deleteUserData(User user) {
		TypedQuery<Feature> query = entityManager.createQuery("Delete from feature as f where f.user = :user" , Feature.class);	
		 query.setParameter("user", user).executeUpdate();		
	}
	
	@Override
	public Boolean canBeDeleted(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
