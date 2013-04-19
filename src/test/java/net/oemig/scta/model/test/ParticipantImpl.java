package net.oemig.scta.model.test;

import net.oemig.scta.model.IParticipant;
import net.oemig.scta.model.data.ExperiementId;

public class ParticipantImpl implements IParticipant {

	public static IParticipant of(String name, ExperiementId experiementId){
		return new ParticipantImpl(name, experiementId);
	}

	private String name;
	private ExperiementId experimentId;
	
	private ParticipantImpl(String name, ExperiementId experiementId){
		this.name=name;
		this.experimentId=experiementId;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public ExperiementId getExperimentId() {
		return experimentId;
	}

}
