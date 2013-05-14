package net.oemig.scta.model.impl.pojo;

import net.oemig.scta.model.IParticipant;
import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.UserName;

public final class PojoParticipantImpl implements IParticipant {

	public static IParticipant create(UserName name, ExperiementId experimentId){
		return new PojoParticipantImpl(name, experimentId);
	}

	private UserName name;
	private ExperiementId experimentId;
	
	//private constructor
	private PojoParticipantImpl(UserName name, ExperiementId experimentId){
		this.name=name;
		this.experimentId=experimentId;
	}
	
	@Override
	public UserName getName() {
		return name;
	}

	@Override
	public ExperiementId getExperimentId() {
		return experimentId;
	}

}
