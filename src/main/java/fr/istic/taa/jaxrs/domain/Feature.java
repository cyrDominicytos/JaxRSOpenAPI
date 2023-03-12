package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * 
 * @author Cyriaque TOSSOU
 * @author Yosser Eljeddi
 *
 */

public class Feature extends Ticket  implements Serializable {
	private String utility;
	private Boolean isBug;
	
	public String getUtility() {
		return utility;
	}
	public void setUtility(String utility) {
		this.utility = utility;
	}
	public Boolean getIsBug() {
		return isBug;
	}
	public void setIsBug(Boolean isBug) {
		this.isBug = isBug;
	}
}
