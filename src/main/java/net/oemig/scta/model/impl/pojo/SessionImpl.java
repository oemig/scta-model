package net.oemig.scta.model.impl.pojo;

import java.util.List;

import net.oemig.scta.model.IRun;
import net.oemig.scta.model.ISession;

public class SessionImpl implements ISession {

	public static SessionImpl of(List<IRun> runs){
		return new SessionImpl(runs);
	}

	private List<IRun> runs;
	
	private SessionImpl(List<IRun> runs){
		this.runs=runs;
	}
	
	@Override
	public String getName() {
		return "TestSession";
	}

	@Override
	public List<IRun> getRuns() {
		return runs;
	}

}
