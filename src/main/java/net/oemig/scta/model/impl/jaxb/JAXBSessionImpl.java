package net.oemig.scta.model.impl.jaxb;

import java.util.ArrayList;
import java.util.List;

import net.oemig.scta.model.IRun;
import net.oemig.scta.model.ISession;
import net.oemig.scta.model.binding.ObjectFactory;
import net.oemig.scta.model.binding.Trace;
import net.oemig.scta.model.binding.Trace.Session;

public final class JAXBSessionImpl implements ISession {
	
	private Session session;
	
	private JAXBSessionImpl(){
		this.session=new ObjectFactory().createTraceSession();//empty init
	}

	@Override
	public String getName() {
		return session.getName();
	}

	@Override
	public List<IRun> getRuns() {
		List<IRun>result=new ArrayList<IRun>();
		for(Trace.Session.Run r:session.getRun()){
			result.add(JAXBRunImpl.builder().run(r).build());
		}
		return result;
	}

	@Override
	public void setName(String aSessionName) {
		session.setName(aSessionName);
	}

	private void setSession(Session aSession){
		session=aSession;
	}
	
	public static Builder builder(){
		return new Builder();
	}
	
	public static class Builder{
		private JAXBSessionImpl s=new JAXBSessionImpl();
		
		public Builder session(Session aSession){
			s.setSession(aSession);
			return this;
		}
		
		public ISession build(){
			return s;
		}
	}
}
