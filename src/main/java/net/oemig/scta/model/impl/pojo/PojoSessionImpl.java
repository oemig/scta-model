package net.oemig.scta.model.impl.pojo;

import java.util.List;

import com.google.common.collect.Lists;

import net.oemig.scta.model.IRun;
import net.oemig.scta.model.ISession;

public final class PojoSessionImpl implements ISession {

	private List<IRun> runs;
	private String name;
	
	//private constructor
	private PojoSessionImpl(){
		runs=Lists.newArrayList();
		name="DefaultSessionName";
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public List<IRun> getRuns() {
		return runs;
	}
	
	private void setRuns(List<IRun> someRuns){
		runs=someRuns;
	}

	@Override
	public void setName(String aSessionName) {
		name=aSessionName;
	}
	
	public static Builder builder(){
		return new Builder();
	}

	public static class Builder{
		private PojoSessionImpl psi=new PojoSessionImpl();
		
		public ISession build(){
			return psi;
		}
		
		public Builder runs(List<IRun> someRuns){
			psi.setRuns(someRuns);
			return this;
		}
		
		public Builder name(String aName){
			psi.setName(aName);
			return this;
		}
	}
}
