package net.oemig.scta.model.impl.jaxb;

import java.util.List;

import net.oemig.scta.model.ISession;
import net.oemig.scta.model.ITrace;
import net.oemig.scta.model.binding.ObjectFactory;
import net.oemig.scta.model.binding.Trace;
import net.oemig.scta.model.binding.Trace.Session;

import com.google.common.collect.Lists;

public final class JAXBTraceImpl implements ITrace {
	
	private Trace trace;
	
	//private constructor
	private JAXBTraceImpl(){
		this.trace=new ObjectFactory().createTrace();//empty default trace
	}
	

	@Override
	public String getName() {
		return trace.getName();
	}

	@Override
	public List<ISession> getSessions() {
		List<ISession>result=Lists.newArrayList();
		for(Session s:trace.getSession()){
			result.add(JAXBSessionImpl.builder().session(s).build());
		}
		return result;
	}
	


	@Override
	public void setName(String aTraceName) {
		trace.setName(aTraceName);
	}
	
	private void setTrace(Trace aTrace){
		trace=aTrace;
	}
	
	public static Builder builder(){
		return new Builder();
	}
	
	public static class Builder{
		
		private JAXBTraceImpl t=new JAXBTraceImpl();
		
		public Builder trace(Trace aTrace){
			t.setTrace(aTrace);
			return this;
		}
		
		public Builder name(String aName){
			t.setName(aName);
			return this;
		}
		
		public ITrace build(){
			return t;
		}
	}
	

}
