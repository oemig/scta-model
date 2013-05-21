package net.oemig.scta.model.impl.pojo;


import net.oemig.scta.model.IRun;
import net.oemig.scta.model.ISession;
import net.oemig.scta.model.ITrace;
import net.oemig.scta.model.ITraceModel;
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

import com.google.common.collect.Lists;

/**
 * In memory model implementation using plain old
 * java objects (POJO).
 * 
 * @author chris
 *
 */
public final class PojoTraceModelImpl implements ITraceModel {

	private IRun currentRun;
	private ISession currentSession;
	private ITrace currentTrace;
	private IExporter exporter;
	
	//private constructor
	private PojoTraceModelImpl(){
		
		currentRun=PojoRunImpl.builder().build();
		currentSession=PojoSessionImpl.builder().runs(Lists.newArrayList(currentRun)).build();
		currentTrace=PojoTraceImpl.builder().sessions(Lists.newArrayList(currentSession)).build();
	}
	
	
	@Override
	public ITrace getCurrentTrace() {
		return currentTrace;
	}

	@Override
	public ISession getCurrentSession() throws NoCurrentSessionSelectedException {
		if(null==currentSession){
			throw new NoCurrentSessionSelectedException("Current Session is null.");
		}
		return currentSession;
	}
	

	@Override
	public IRun getCurrentRun() throws NoCurrentRunSelectedException {
		if(null==currentRun){
			throw new NoCurrentRunSelectedException("Current run is null");
		}
		return currentRun;
	}
	
	@Override
	public void addParticipant(UserName name, ExperiementId experimentId) throws NoCurrentRunSelectedException {
		getCurrentRun().getParticipants().add(PojoParticipantImpl.create(name, experimentId));
	}

	@Override
	public void addCountData(UserName participantName, String letter,
			int quantity) throws NoCurrentRunSelectedException {
		getCurrentRun().getCountData().add(PojoCountDataImpl.of(letter, quantity, participantName));
	}

	@Override
	public void addResponseData(UserName participantName, boolean isCorrect,
			Millisecond responseTime, QuestionType questionType) throws NoCurrentRunSelectedException {
		getCurrentRun().getResponseData().add(PojoResponseDataImpl.of(responseTime,isCorrect,participantName,questionType ));
	}

	@Override
	public void newSession(String aSessionName)
			throws SessionAlreadyExistsException {
		try{
			findSessionByName(aSessionName);
			throw new SessionAlreadyExistsException(aSessionName);
		}catch(SessionNotFoundException e){
			ISession s=PojoSessionImpl.builder().name(aSessionName).build();
			
			currentSession=s;
			getCurrentTrace().getSessions().add(s);
		}
	}


	@Override
	public void newRun(String aSessionName) throws SessionNotFoundException {
		ISession s=findSessionByName(aSessionName);
		IRun r=PojoRunImpl.builder().build();
		currentRun=r;
		s.getRuns().add(r);
	}
	//FIME move to super class
	private ISession findSessionByName(String aSessionName) throws SessionNotFoundException{
		for(ISession s:getCurrentTrace().getSessions()){
			if(s.getName().equals(aSessionName)){
				return s;
			}
		}
		throw new SessionNotFoundException(aSessionName);
	}
	@Override
	public void save(final String fileName) throws OperationNotSupportedException{
		throw new OperationNotSupportedException("PojoTraceModelImpl only allows in memory use");

	}

	@Override
	//FIXME place in abstract model impl
	public void export() throws OperationNotSupportedException{
		if(null==exporter){
			throw new OperationNotSupportedException("Exporter is null.");
		}else{
			exporter.export(this);
		}
	}


	private void setExporter(IExporter anExporter){
		exporter=anExporter;
	}
	
	public static Builder builder(){
		return new Builder();
	}

	public static class Builder{
		private PojoTraceModelImpl m=new PojoTraceModelImpl();
		
		public Builder exporter(IExporter anExporter){
			m.setExporter(anExporter);
			return this;
		}
		
		public Builder traceName(String aTraceName){
			m.getCurrentTrace().setName(aTraceName);
			return this;
		}
		
		public Builder sessionName(String aSessionName) throws NoCurrentSessionSelectedException{
			m.getCurrentSession().setName(aSessionName);
			return this;
		}
		
		public Builder newRun(String aSessionName) throws SessionNotFoundException{
			m.newRun(aSessionName);
			return this;
		}
		
		public Builder newSession(String aSessionName) throws SessionAlreadyExistsException{
			m.newSession(aSessionName);
			return this;
		}
		
		public ITraceModel build(){
			return m;
		}
	}


}
