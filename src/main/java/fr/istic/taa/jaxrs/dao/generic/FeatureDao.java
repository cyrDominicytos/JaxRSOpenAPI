package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Feature;

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
}
