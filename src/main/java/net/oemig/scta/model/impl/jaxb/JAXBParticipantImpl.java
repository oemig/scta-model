package net.oemig.scta.model.impl.jaxb;

import net.oemig.scta.model.IParticipant;
import net.oemig.scta.model.binding.ObjectFactory;
import net.oemig.scta.model.binding.Trace.Session.Run.Participant;
import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.UserName;

public final class JAXBParticipantImpl implements IParticipant {

	private Participant participant;
	
	private JAXBParticipantImpl(){
		this.participant=new ObjectFactory().createTraceSessionRunParticipant();
	}

	private void setParticipant(Participant aParticipant){
		participant=aParticipant;
	}
	
	@Override
	public UserName getName() {
		return UserName.of(participant.getName());
	}

	@Override
	public ExperiementId getExperimentId() {
		return ExperiementId.of(participant.getExperimentId());
	}
	
	public static Builder builder(){
		return new Builder();
	}
	
	public static class Builder{
		private JAXBParticipantImpl p=new JAXBParticipantImpl();
		
		public Builder participant(Participant aParticipant){
			p.setParticipant(aParticipant);
			return this;
		}
		public IParticipant build(){
			return p;
		}
		
	}
}
