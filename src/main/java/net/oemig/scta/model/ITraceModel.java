package net.oemig.scta.model;

import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.data.UserName;
import net.oemig.scta.model.exception.NoCurrentRunSelectedException;
import net.oemig.scta.model.exception.NoCurrentSessionSelectedException;
import net.oemig.scta.model.exception.OperationNotSupportedException;
import net.oemig.scta.model.exception.SessionAlreadyExistsException;
import net.oemig.scta.model.exception.SessionNotFoundException;
import net.oemig.scta.model.exporter.IExporter;

/**
 * {@link ITraceModel} is the data model interface for the SCTA
 * for which there may be different implementation (the default
 * being a JAXB implemention.
 * 
 * @author chris
 *
 */
public interface ITraceModel {

	public ITrace getCurrentTrace();


	public ISession getCurrentSession() throws NoCurrentSessionSelectedException;


	public IRun getCurrentRun() throws NoCurrentRunSelectedException;

	/**
	 * Adds the given participant data to this model's current run.
	 * @param name
	 * @param experimentId
	 * @throws NoCurrentRunSelectedException 
	 */
	public void addParticipant(
			final UserName name, 
			final ExperiementId experimentId) throws NoCurrentRunSelectedException;

	/**
	 * Adds the provided count data to current run of 
	 * this model.
	 * @param participantName
	 * @param letter
	 * @param quantity
	 * @throws NoCurrentRunSelectedException 
	 */
	public void addCountData(
			final UserName participantName, 
			final String letter, 
			final int quantity) throws NoCurrentRunSelectedException;

	/**
	 * Adds the provided response data to the current run.
	 * @param participantName
	 * @param isCorrect
	 * @param responseTime
	 * @param questionType
	 * @throws NoCurrentRunSelectedException 
	 */
	public void addResponseData(
			final UserName participantName, 
			final boolean isCorrect,
			final Millisecond responseTime,
			final QuestionType questionType) throws NoCurrentRunSelectedException;

	/**
	 * Saves this model to the standard of the respective 
	 * model implementation, for instance, JAXB and XML.
	 */
	public void save(final String fileName) throws OperationNotSupportedException;
	
	/**
	 * Exports the this model to the {@link IExporter} instance
	 * referenced by this class.
	 */
	public void export() throws OperationNotSupportedException;


	public void newSession(String aSessionName) throws SessionAlreadyExistsException;


	public void newRun(String aSessionName) throws SessionNotFoundException;
	
	

}