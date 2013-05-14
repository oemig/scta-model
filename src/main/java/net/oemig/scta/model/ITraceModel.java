package net.oemig.scta.model;

import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.data.UserName;
import net.oemig.scta.model.exception.OperationNotSupportedException;
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


	public ISession getCurrentSession();


	public IRun getCurrentRun();

	/**
	 * Adds the given participant data to this model's current run.
	 * @param name
	 * @param experimentId
	 */
	public void addParticipant(
			final UserName name, 
			final ExperiementId experimentId);

	/**
	 * Adds the provided count data to current run of 
	 * this model.
	 * @param participantName
	 * @param letter
	 * @param quantity
	 */
	public void addCountData(
			final UserName participantName, 
			final String letter, 
			final int quantity);

	/**
	 * Adds the provided response data to the current run.
	 * @param participantName
	 * @param isCorrect
	 * @param responseTime
	 * @param questionType
	 */
	public void addResponseData(
			final UserName participantName, 
			final boolean isCorrect,
			final Millisecond responseTime,
			final QuestionType questionType);

	/**
	 * Saves this model to the standard of the respective 
	 * model implementation, for instance, JAXB and XML.
	 */
	public void save() throws OperationNotSupportedException;
	
	/**
	 * Exports the this model to the {@link IExporter} instance
	 * referenced by this class.
	 */
	public void export() throws OperationNotSupportedException;

}