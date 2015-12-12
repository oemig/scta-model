package net.oemig.scta.model.data;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * User representation in the SCTA model.
 * A user registers and takes part in an experiment.
 * 
 * @author coemig@acm.org
 *
 */
public class User {
	
	public static User of(String aName){
		return new User(aName);
	}

	private String name;
	
	private User(String aName){
		name=aName;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return ToStringBuilder.reflectionToString(this);
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
