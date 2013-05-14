package net.oemig.scta.model.impl.pojo;

import java.util.ArrayList;
import java.util.List;

import net.oemig.scta.model.ICountData;
import net.oemig.scta.model.IParticipant;
import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.IRun;

public final class PojoRunImpl implements IRun {

	public static IRun create(List<ICountData>countData,List<IResponseData> responseData, List<IParticipant>participants){
		return new PojoRunImpl(countData, responseData, participants);
	}

	public static IRun createEmpty(){
		return new PojoRunImpl(new ArrayList<ICountData>(), 
				new ArrayList<IResponseData>(), 
				new ArrayList<IParticipant>());
	}
	
	private List<ICountData> countData;
	private List<IResponseData> responseData;
	private List<IParticipant> participants;
	
	//private constructor
	private PojoRunImpl(List<ICountData>countData,List<IResponseData> responseData, List<IParticipant>participants){
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
