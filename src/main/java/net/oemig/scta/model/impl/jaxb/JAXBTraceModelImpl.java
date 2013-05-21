package net.oemig.scta.model.impl.jaxb;

import java.io.File;
import java.io.InputStream;

import javax.xml.bind.JAXB;

import net.oemig.scta.model.IRun;
import net.oemig.scta.model.ISession;
import net.oemig.scta.model.ITrace;
import net.oemig.scta.model.ITraceModel;
import net.oemig.scta.model.binding.ObjectFactory;
import net.oemig.scta.model.binding.Trace;
import net.oemig.scta.model.binding.Trace.Session;
import net.oemig.scta.model.binding.Trace.Session.Run;
import net.oemig.scta.model.binding.Trace.Session.Run.CountData;
import net.oemig.scta.model.binding.Trace.Session.Run.Participant;
import net.oemig.scta.model.binding.Trace.Session.Run.ResponseData;
import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.Millisecond;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.data.UserName;
import net.oemig.scta.model.exception.NoCurrentRunSelectedException;
import net.oemig.scta.model.exception.NoCurrentSessionSelectedException;
import net.oemig.scta.model.exception.OperationNotSupportedException;
import net.oemig.scta.model.exception.SessionAlreadyExistsException;
import net.oemig.scta.model.exception.SessionNotFoundException;
import net.oemig.scta.model.exception.TraceFileNotFoundExeption;
import net.oemig.scta.model.exporter.IExporter;

public final class JAXBTraceModelImpl implements ITraceModel {

	private ObjectFactory objectFactory;
	private Trace currentTrace;
	private Session currentSession;
	private Run currentRun;

	private IExporter exporter;

	
	//private constructor
	private JAXBTraceModelImpl() {
		this.objectFactory = new ObjectFactory();

		currentTrace=this.objectFactory.createTrace();
		currentSession=this.objectFactory.createTraceSession();
		currentTrace.getSession().add(currentSession);

		currentRun=this.objectFactory.createTraceSessionRun();
		currentSession.getRun().add(currentRun);
	}

	@Override
	public ITrace getCurrentTrace() {
		return JAXBTraceImpl.builder().trace(currentTrace).build();
	}

	@Override
	public ISession getCurrentSession() throws NoCurrentSessionSelectedException{
		if(null==currentSession){
			throw new NoCurrentSessionSelectedException("Current session is null.");
		}
		return JAXBSessionImpl.builder().session(currentSession).build();
	}


	@Override
	public IRun getCurrentRun() throws NoCurrentRunSelectedException {
		if(null==currentRun){
			throw new NoCurrentRunSelectedException("Current run is null.");
		}
		return JAXBRunImpl.builder().run(currentRun).build();
	}


	@Override
	public void addParticipant(final UserName name, final ExperiementId experimentId) {
		Participant participant = objectFactory
				.createTraceSessionRunParticipant();
		participant.setName(name.toString());
		participant.setExperimentId(experimentId.toString());
		currentRun.getParticipant().add(participant);
	}

	@Override
	public void addCountData(
			final UserName participantName, 
			final String letter, 
			final int quantity) {
		CountData countData = objectFactory.createTraceSessionRunCountData();
		countData.setParticipant(participantName.toString());
		countData.setLetter(letter);
		countData.setQuantity(quantity);

		currentRun.getCountData().add(countData);

	}

	@Override
	public void addResponseData(
			final UserName participantName, 
			final boolean isCorrect,
			final Millisecond responseTime,
			final QuestionType questionType) {
		ResponseData responseData = this.objectFactory
				.createTraceSessionRunResponseData();
		responseData.setParticipant(participantName.toString());
		responseData.setCorrect(isCorrect);
		responseData.setResponsetime(Integer.valueOf(responseTime.intValue()));
		responseData.setQuestionType(questionType.name());

		currentRun.getResponseData().add(responseData);

	}
	@Override
	public void newSession(final String aSessionName) throws SessionAlreadyExistsException{
		try{
			findSessionByName(aSessionName);
			throw new SessionAlreadyExistsException(aSessionName);
		}catch(SessionNotFoundException e){
			Session s=new ObjectFactory().createTraceSession();
			s.setName(aSessionName);
			
			currentSession=s;
			currentTrace.getSession().add(s);
		}
	}
	@Override
	public void newRun(final String aSessionName) throws SessionNotFoundException{
		Session s=findSessionByName(aSessionName);
		Run r=new ObjectFactory().createTraceSessionRun();
		currentRun=r;
		s.getRun().add(r);
	}
	
	private Session findSessionByName(String aSessionName) throws SessionNotFoundException{
		for(Session s:currentTrace.getSession()){
			if(s.getName().equals(aSessionName)){
				return s;
			}
		}
		throw new SessionNotFoundException(aSessionName);
	}
	@Override
	public void save(final String fileName) throws OperationNotSupportedException{
		//FIXME no gui in a model class!!
//		JFileChooser fc=new JFileChooser();
//		fc.setDialogType(JFileChooser.SAVE_DIALOG);
//		int state=fc.showSaveDialog(null);
//		if(JFileChooser.APPROVE_OPTION==state){
//			JAXB.marshal(currentTrace, fc.getSelectedFile());
//		}
		
		JAXB.marshal(currentTrace, new File(fileName));
				
	}
	
	@Override
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
	
	private void load(final InputStream anIs) throws TraceFileNotFoundExeption{
		try{
			//try to find trace file
			
			currentTrace=JAXB.unmarshal(anIs, Trace.class);
			
			//there is no current run
			currentRun=null;
			currentSession=null;
			//there is no current session
		}catch(Exception e){
			//not found.. create a new one
			throw new TraceFileNotFoundExeption(currentTrace.getName());
		}
	}
	
	public static Builder builder(){
		return new Builder();
	}
	
	public static class Builder{
		private JAXBTraceModelImpl m=new JAXBTraceModelImpl();
		
		
		public Builder exporter(IExporter anExporter){
			m.setExporter(anExporter);
			return this;
		}
		
		public Builder load(InputStream anIs) throws TraceFileNotFoundExeption{
			m.load(anIs);
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
		
		/**
		 * Add a new run to the current session specified by the
		 * session name.
		 * @param aSessionName
		 * @return
		 * @throws SessionNotFoundException
		 */
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
