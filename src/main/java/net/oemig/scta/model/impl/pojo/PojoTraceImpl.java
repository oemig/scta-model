package net.oemig.scta.model.impl.pojo;

import java.util.ArrayList;
import java.util.List;

import net.oemig.scta.model.ISession;
import net.oemig.scta.model.ITrace;

public final class PojoTraceImpl implements ITrace{

	public static ITrace create(List<ISession> sessions){
		return new PojoTraceImpl(sessions);
	}
	
	public static ITrace createEmtpy(){
		return new PojoTraceImpl(new ArrayList<ISession>());
	}

	private List<ISession> sessions;
	
	private PojoTraceImpl(List<ISession> sessions){
		this.sessions=sessions;
	}
	
	
	@Override
	public String getName() {
		return "TestTrace";
	}

	@Override
	public List<ISession> getSessions() {
		return sessions;
	}

}
