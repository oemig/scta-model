package net.oemig.scta.model.impl.jaxb;

import net.oemig.scta.model.IParticipant;
import net.oemig.scta.model.binding.Trace.Session.Run.Participant;
import net.oemig.scta.model.data.ExperiementId;

public final class JAXBParticipantImpl implements IParticipant {

	public static JAXBParticipantImpl of(Participant aParticipant){
		return new JAXBParticipantImpl(aParticipant);
	}

	private Participant participant;
	
	private JAXBParticipantImpl(Participant aParticipant){
		this.participant=aParticipant;
	}

	@Override
	public String getName() {
		return participant.getName();
	}

	@Override
	public ExperiementId getExperimentId() {
		return ExperiementId.of(participant.getExperimentId());
	}
}
