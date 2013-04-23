package net.oemig.scta.model.test;

import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;

public class ResponseDataImpl implements IResponseData {

	public static IResponseData of(Millisecond responseTime, boolean isCorrect, String participantName, QuestionType questionType){
		return new ResponseDataImpl(responseTime, isCorrect, participantName, questionType);
	}

	private Millisecond responseTime;
	private boolean correct;
	private String participantName;
	private QuestionType questionType;
	
	private ResponseDataImpl(Millisecond responseTime, boolean isCorrect, String participantName, QuestionType questionType){
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
	public String getParticipantName() {
		return participantName;
	}

	@Override
	public QuestionType getQuestionType() {
		return questionType;
	}

}
