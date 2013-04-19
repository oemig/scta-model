package net.oemig.scta.model.impl;

import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.binding.Trace.Session.Run.ResponseData;
import net.oemig.scta.model.data.QuestionType;

public final class JAXBResponseData implements IResponseData {

	public static JAXBResponseData of(ResponseData aResponseData){
		return new JAXBResponseData(aResponseData);
	}

	private ResponseData responseData;
	
	private JAXBResponseData(ResponseData aResponseData){
		this.responseData=aResponseData;
	}
	
	@Override
	public int getResponseTime() {
		return responseData.getResponsetime().intValue();
	}

	@Override
	public boolean isCorrect() {
		return responseData.isCorrect().booleanValue();
	}

	@Override
	public String getParticipantName() {
		return responseData.getParticipant();
	}

	@Override
	public QuestionType getQuestionType() {
		return QuestionType.valueOf(responseData.getQuestionType());
	}

}
