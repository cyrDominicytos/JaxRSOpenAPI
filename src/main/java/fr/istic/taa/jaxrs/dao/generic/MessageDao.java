package fr.istic.taa.jaxrs.dao.generic;

import fr.istic.taa.jaxrs.domain.Message;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */
public class MessageDao extends AbstractJpaDao<Long, Message>{
	public MessageDao() {
		this.setClazz(Message.class);
	}

	@Override
	public Boolean canBeDeleted(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}

