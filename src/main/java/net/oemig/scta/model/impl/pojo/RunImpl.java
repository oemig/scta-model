package net.oemig.scta.model.impl.pojo;

import java.util.List;

import net.oemig.scta.model.ICountData;
import net.oemig.scta.model.IParticipant;
import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.IRun;

public final class RunImpl implements IRun {

	public static IRun of(List<ICountData>countData,List<IResponseData> responseData, List<IParticipant>participants){
		return new RunImpl(countData, responseData, participants);
	}

	private List<ICountData> countData;
	private List<IResponseData> responseData;
	private List<IParticipant> participants;
	
	private RunImpl(List<ICountData>countData,List<IResponseData> responseData, List<IParticipant>participants){
		this.countData=countData;
		this.responseData=responseData;
		this.participants=participants;
	}
	@Override
	public List<ICountData> getCountData() {
		return countData;
	}

	@Override
	public List<IResponseData> getResponseData() {
		return responseData;
	}

	@Override
	public List<IParticipant> getParticipants() {
		return participants;
	}

}
