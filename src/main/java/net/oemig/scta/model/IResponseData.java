package net.oemig.scta.model;

import net.oemig.scta.model.data.QuestionType;

public interface IResponseData {

	public int getResponseTime();
	public boolean isCorrect();
	public String getParticipantName();
	public QuestionType getQuestionType();
}
