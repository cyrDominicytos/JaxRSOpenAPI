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

	@Override
	public Boolean canBeDeleted(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

