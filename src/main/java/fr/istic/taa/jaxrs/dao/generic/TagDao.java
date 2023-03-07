package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Tag;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
public class TagDao extends AbstractJpaDao<Long, Tag>{
	public TagDao() {
		this.setClazz(Tag.class);
	}

	@Override
	public Boolean canBeDeleted(Long id) {
		BugDao bugDao = new BugDao();
		return bugDao.hasLinkedWithTag(id);
	}
	
	
}
