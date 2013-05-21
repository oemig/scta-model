package net.oemig.scta.model.impl.pojo;

import java.util.List;

import net.oemig.scta.model.ICountData;
import net.oemig.scta.model.IParticipant;
import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.IRun;

import com.google.common.collect.Lists;

public final class PojoRunImpl implements IRun {

	private List<ICountData> countData;
	private List<IResponseData> responseData;
	private List<IParticipant> participants;
	
	//private constructor
	private PojoRunImpl(){
		this.countData=Lists.newArrayList();
		this.responseData=Lists.newArrayList();
		this.participants=Lists.newArrayList();
	}
	@Override
	public List<ICountData> getCountData() {
		return countData;
	}
	
	private void setCountData(List<ICountData> someCoundData){
		countData=someCoundData;
	}

	@Override
	public List<IResponseData> getResponseData() {
		return responseData;
	}
	
	private void setResponseData(List<IResponseData> someResponseData){
		responseData=someResponseData;
	}

	@Override
	public List<IParticipant> getParticipants() {
		return participants;
	}
	
	private void setParticipants(List<IParticipant> someParticipants){
		participants=someParticipants;
	}

	public static Builder builder(){
		return new Builder();
	}
	
	public static class Builder{
		private PojoRunImpl pri=new PojoRunImpl();
		
		public IRun build(){
			return pri;
		}
		
		public Builder countData(List<ICountData> someCountData){
			pri.setCountData(someCountData);
			return this;
		}
		
		public Builder responseData(List<IResponseData> someResponseData){
			pri.setResponseData(someResponseData);
			return this;
		}
		
		public Builder participants(List<IParticipant> someParticipants){
			pri.setParticipants(someParticipants);
			return this;
		}
	}
}
