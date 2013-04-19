package net.oemig.scta.model.impl;

import java.util.ArrayList;
import java.util.List;

import net.oemig.scta.model.ISession;
import net.oemig.scta.model.ITrace;
import net.oemig.scta.model.binding.Trace;
import net.oemig.scta.model.binding.Trace.Session;

public final class JAXBTraceImpl implements ITrace {
	public static JAXBTraceImpl of(Trace aTrace){
		return new JAXBTraceImpl(aTrace);
	}
	
	private Trace trace;
	
	private JAXBTraceImpl(Trace aTrace){
		this.trace=aTrace;
	}
	
	public JAXBTraceImpl name(String aName){
		trace.setName(aName);
		return this;
	}

	@Override
	public String getName() {
		return trace.getName();
	}

	@Override
	public List<ISession> getSessions() {
		List<ISession>result=new ArrayList<ISession>();
		for(Session s:trace.getSession()){
			result.add(JAXBSessionImpl.of(s));
		}
		return result;
	}
	

}
