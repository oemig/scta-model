package net.oemig.scta.model.data;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * {@link ExperiementId} class added for type safety reasons.
 * 
 * @author oemig
 *
 */
public final class ExperiementId implements Serializable {
	
	
	private static final long serialVersionUID = 3090114892860059039L;

	public static ExperiementId TEST=of("test");
	
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
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this,obj);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	
}
