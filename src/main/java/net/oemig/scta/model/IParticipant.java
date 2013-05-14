package net.oemig.scta.model;

import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.UserName;

public interface IParticipant {

	public UserName getName();
	public ExperiementId getExperimentId();
}
