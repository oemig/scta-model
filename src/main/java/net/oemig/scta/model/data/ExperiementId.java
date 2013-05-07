package net.oemig.scta.model.data;

import java.io.Serializable;

/**
 * {@link ExperiementId} class added for type safety reasons.
 * 
 * @author oemig
 *
 */
public final class ExperiementId implements Serializable {
	
	
	private static final long serialVersionUID = 3090114892860059039L;

	public static ExperiementId of(final String anId){
		return new ExperiementId(anId);
	}

	private String id;
	
	private ExperiementId(final String anId){
		id=anId;
	}

	public String toString(){
		return id;
	}
}
