package net.oemig.scta.model;

import java.util.List;

public interface IRun {

	public List<ICountData> getCountData();
	public List<IResponseData> getResponseData();
	public List<IParticipant> getParticipants();
}
