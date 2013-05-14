package net.oemig.scta.model.impl.jaxb;

import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.binding.Trace.Session.Run.ResponseData;
import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.data.UserName;

public final class JAXBResponseData implements IResponseData {

	public static JAXBResponseData of(ResponseData aResponseData){
		return new JAXBResponseData(aResponseData);
	}

	private ResponseData responseData;
	
	private JAXBResponseData(ResponseData aResponseData){
		this.responseData=aResponseData;
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

}
