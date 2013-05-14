package net.oemig.scta.model.impl.pojo;

import java.util.ArrayList;
import java.util.List;

import net.oemig.scta.model.IRun;
import net.oemig.scta.model.ISession;

public final class PojoSessionImpl implements ISession {

	public static ISession create(List<IRun> runs){
		return new PojoSessionImpl(runs);
	}
	
	public static ISession createEmpty(){
		return new PojoSessionImpl(new ArrayList<IRun>());
	}

	private List<IRun> runs;
	
	//private constructor
	private PojoSessionImpl(List<IRun> runs){
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
