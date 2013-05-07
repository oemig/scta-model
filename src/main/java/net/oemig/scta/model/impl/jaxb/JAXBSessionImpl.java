package net.oemig.scta.model.impl.jaxb;

import java.util.ArrayList;
import java.util.List;

import net.oemig.scta.model.IRun;
import net.oemig.scta.model.ISession;
import net.oemig.scta.model.binding.Trace;
import net.oemig.scta.model.binding.Trace.Session;

public final class JAXBSessionImpl implements ISession {
	
	public static JAXBSessionImpl of(Session aSession){
		return new JAXBSessionImpl(aSession);
	}


	private Session session;
	
	private JAXBSessionImpl(Session aSession){
		this.session=aSession;
	}

	@Override
	public String getName() {
		return session.getName();
	}

	@Override
	public List<IRun> getRuns() {
		List<IRun>result=new ArrayList<IRun>();
		for(Trace.Session.Run r:session.getRun()){
			result.add(JAXBRunImpl.of(r));
		}
		return null;
	}
	
}
