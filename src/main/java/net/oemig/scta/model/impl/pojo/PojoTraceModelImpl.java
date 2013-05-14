package net.oemig.scta.model.impl.pojo;


import com.google.common.collect.Lists;

import net.oemig.scta.model.IRun;
import net.oemig.scta.model.ISession;
import net.oemig.scta.model.ITrace;
import net.oemig.scta.model.ITraceModel;
import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.data.UserName;
import net.oemig.scta.model.exception.OperationNotSupportedException;

/**
 * In memory model implementation using plain old
 * java objects (POJO).
 * 
 * @author chris
 *
 */
public final class PojoTraceModelImpl implements ITraceModel {

	public static PojoTraceModelImpl create(
			final String aTraceName, 
			final String aSessionName){
		
		return new PojoTraceModelImpl(aTraceName,aSessionName);
	}

	private IRun currentRun;
	private ISession currentSession;
	private ITrace currentTrace;
	
	//private constructor
	private PojoTraceModelImpl(
			final String aTraceName, 
			final String aSessionName){
		
		currentRun=PojoRunImpl.createEmpty();
		currentSession=PojoSessionImpl.create(Lists.newArrayList(currentRun));
		currentTrace=PojoTraceImpl.create(Lists.newArrayList(currentSession));
	}
	
	
	@Override
	public ITrace getCurrentTrace() {
		return currentTrace;
	}

	@Override
	public ISession getCurrentSession() {
		return currentSession;
	}

	@Override
	public IRun getCurrentRun() {
		return currentRun;
	}

	@Override
	public void addParticipant(UserName name, ExperiementId experimentId) {
		getCurrentRun().getParticipants().add(PojoParticipantImpl.create(name, experimentId));
	}

	@Override
	public void addCountData(UserName participantName, String letter,
			int quantity) {
		getCurrentRun().getCountData().add(PojoCountDataImpl.of(letter, quantity, participantName));
	}

	@Override
	public void addResponseData(UserName participantName, boolean isCorrect,
			Millisecond responseTime, QuestionType questionType) {
		getCurrentRun().getResponseData().add(PojoResponseDataImpl.of(responseTime,isCorrect,participantName,questionType ));
	}

	@Override
	public void save() throws OperationNotSupportedException{
		throw new OperationNotSupportedException("PojoTraceModelImpl only allows in memory use");

	}

	@Override
	public void export() throws OperationNotSupportedException {
		// TODO Auto-generated method stub

	}

}
