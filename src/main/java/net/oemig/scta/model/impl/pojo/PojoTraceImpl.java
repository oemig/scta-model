package net.oemig.scta.model.impl.pojo;

import java.util.List;

import net.oemig.scta.model.ISession;
import net.oemig.scta.model.ITrace;

import com.google.common.collect.Lists;

public final class PojoTraceImpl implements ITrace{

	private List<ISession> sessions;
	private String name;
	
	private PojoTraceImpl(){
		this.sessions=Lists.newArrayList();
		name="DefaultTraceName";
	}
	
	
	@Override
	public String getName() {
		return name;
	}
	
	

	@Override
	public List<ISession> getSessions() {
		return sessions;
	}

	private void setSessions(List<ISession> someSessions){
		sessions=someSessions;
	}
	

	@Override
	public void setName(String aTraceName) {
		name=aTraceName;
	}
	
	public static Builder builder(){
		return new Builder();
	}
	
	public static class Builder{
		PojoTraceImpl pti=new PojoTraceImpl();
		
		public ITrace build(){
			return pti;
		}
		
		public Builder sessions(List<ISession> someSessions){
			pti.setSessions(someSessions);
			return this;
		}
		
		public Builder name(String aName){
			pti.setName(aName);
			return this;
		}
	}

}
