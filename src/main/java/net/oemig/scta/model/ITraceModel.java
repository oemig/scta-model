package net.oemig.scta.model;

import net.oemig.scta.model.binding.Trace;
import net.oemig.scta.model.binding.Trace.Session;
import net.oemig.scta.model.binding.Trace.Session.Run;
import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.data.UserName;

public interface ITraceModel {

	public Trace getCurrentTrace();


	public Session getCurrentSession();


	public Run getCurrentRun();


	public void addParticipant(final UserName name, 
			final ExperiementId experimentId);

	public void addCountData(
			final UserName participantName, 
			final String letter, 
			final int quantity);

	public void addResponseData(
			final UserName participantName, 
			final boolean isCorrect,
			final int responseTime,
			final QuestionType questionType);

	public void save();
	
	public void export();

}