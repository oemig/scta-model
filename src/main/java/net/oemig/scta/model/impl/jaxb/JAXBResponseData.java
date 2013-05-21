package net.oemig.scta.model.impl.jaxb;

import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.binding.ObjectFactory;
import net.oemig.scta.model.binding.Trace.Session.Run.ResponseData;
import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.data.UserName;

public final class JAXBResponseData implements IResponseData {

	private ResponseData responseData;
	
	private JAXBResponseData(){
		this.responseData=new ObjectFactory().createTraceSessionRunResponseData();
	}
	
	private void setResponseData(ResponseData aResponseData){
		responseData=aResponseData;
	}
	
	@Override
	public Millisecond getResponseTime() {
		return Millisecond.of(responseData.getResponsetime().intValue());
	}

	@Override
	public boolean isCorrect() {
		return responseData.isCorrect().booleanValue();
	}

	@Override
	public UserName getParticipantName() {
		return UserName.of(responseData.getParticipant());
	}

	@Override
	public QuestionType getQuestionType() {
		return QuestionType.valueOf(responseData.getQuestionType());
	}
	
	public static Builder builder(){
		return new Builder();
	}
	
	public static class Builder{
		private JAXBResponseData r=new JAXBResponseData();
		
		public Builder responseData(ResponseData aResponseData){
			r.setResponseData(aResponseData);
			return this;
		}
		public IResponseData build(){
			return r;
		}
	}

}
