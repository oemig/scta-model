package net.oemig.scta.model.data;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * {@link UserName} class added for type safety reasons.
 * 
 * @author oemig
 *
 */
public final class UserName implements Serializable {
	
	private static final long serialVersionUID = 2785306846644253244L;

	public static UserName of(final String aName){
		return new UserName(aName);
	}
	
	public static final UserName TIM =of("tim");
	public static final UserName TINA=of("tina");
	public static final UserName JEFF=of("jeff");
	

	private String userName;
	
	private UserName(final String aName){
		userName=aName;
	}
	
	public String toString(){
		return userName;
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
