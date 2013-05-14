package net.oemig.scta.model.impl.pojo;

import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.data.UserName;

public final class PojoResponseDataImpl implements IResponseData {

	public static IResponseData of(Millisecond responseTime, boolean isCorrect, UserName participantName, QuestionType questionType){
		return new PojoResponseDataImpl(responseTime, isCorrect, participantName, questionType);
	}

	private Millisecond responseTime;
	private boolean correct;
	private UserName participantName;
	private QuestionType questionType;
	
	//private constructor
	private PojoResponseDataImpl(Millisecond responseTime, boolean isCorrect, UserName participantName, QuestionType questionType){
		this.responseTime=responseTime;
		this.correct=isCorrect;
		this.participantName=participantName;
		this.questionType=questionType;
	}
	@Override
	public Millisecond getResponseTime() {
		return responseTime;
	}

	@Override
	public boolean isCorrect() {
		return correct;
	}

	@Override
	public UserName getParticipantName() {
		return participantName;
	}

	@Override
	public QuestionType getQuestionType() {
		return questionType;
	}

}
