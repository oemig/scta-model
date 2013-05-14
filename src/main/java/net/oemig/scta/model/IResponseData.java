package net.oemig.scta.model;

import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.data.UserName;

public interface IResponseData {

	public Millisecond getResponseTime();
	public boolean isCorrect();
	public UserName getParticipantName();
	public QuestionType getQuestionType();
}
