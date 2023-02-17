package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Support;
/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
public class SupportDao extends AbstractJpaDao<Long, Support> {
	
	public SupportDao() {
		this.setClazz(Support.class);
	}
	
}

