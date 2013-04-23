package net.oemig.scta.model;

import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;

public interface IResponseData {

	public Millisecond getResponseTime();
	public boolean isCorrect();
	public String getParticipantName();
	public QuestionType getQuestionType();
}
