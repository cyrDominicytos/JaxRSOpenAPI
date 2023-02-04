package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Bug;

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
}
