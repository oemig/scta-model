package net.oemig.scta.model.impl;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXB;

import net.oemig.scta.model.ITraceModel;
import net.oemig.scta.model.binding.ObjectFactory;
import net.oemig.scta.model.binding.Trace;
import net.oemig.scta.model.binding.Trace.Session;
import net.oemig.scta.model.binding.Trace.Session.Run;
import net.oemig.scta.model.binding.Trace.Session.Run.CountData;
import net.oemig.scta.model.binding.Trace.Session.Run.Participant;
import net.oemig.scta.model.binding.Trace.Session.Run.ResponseData;
import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.data.UserName;
import net.oemig.scta.model.exporter.IExporter;

public class JAXBTraceModelImpl implements ITraceModel {

	private static final String TRACE_FILE_PREFIX = "scta_";

	private ObjectFactory objectFactory;
	private Trace currentTrace;
	private Session currentSession;
	private Run currentRun;

	private IExporter exporter;

	private String traceFileDirectory;
	
	public static JAXBTraceModelImpl with(String aTraceFileDirectory, IExporter anExporter){
		return new JAXBTraceModelImpl(aTraceFileDirectory, anExporter);
	}
	
	private JAXBTraceModelImpl(String aTraceFileDirecotry, IExporter anExporter) {
		traceFileDirectory=aTraceFileDirecotry;
		exporter=anExporter;
		this.objectFactory = new ObjectFactory();
		// is there an existing trace file
		// yes
		// load trace file
		// select existing session
		// yes
		// no
		// create new session object
		// no
		// create new trace object
		// create new session object
		String traceName = JOptionPane
				.showInputDialog("Please enter a trace name!");
		try{
			//try to find trace file
			File traceFile=new File(traceFileDirectory+File.separator+TRACE_FILE_PREFIX+traceName+".xml");
			setCurrentTrace(JAXB.unmarshal(traceFile, Trace.class));
		}catch(Exception e){
			//not found.. create a new one
			setCurrentTrace(this.objectFactory.createTrace());
			getCurrentTrace().setName(traceName);
		}

		String sessionName = JOptionPane
				.showInputDialog("Please enter a session name!");

		setCurrentSession(this.objectFactory.createTraceSession());
		getCurrentSession().setName(sessionName);
		getCurrentTrace().getSession().add(getCurrentSession());

		setCurrentRun(this.objectFactory.createTraceSessionRun());
		getCurrentSession().getRun().add(getCurrentRun());

	}

	/* (non-Javadoc)
	 * @see net.oemig.scta.tracer.model.ITraceModel#getCurrentTrace()
	 */
	@Override
	public Trace getCurrentTrace() {
		return currentTrace;
	}

	private void setCurrentTrace(Trace currentTrace) {
		this.currentTrace = currentTrace;
	}

	@Override
	public Session getCurrentSession() {
		return currentSession;
	}

	private void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	@Override
	public Run getCurrentRun() {
		return currentRun;
	}

	private void setCurrentRun(Run currentRun) {
		this.currentRun = currentRun;
	}

	@Override
	public void addParticipant(final UserName name, final ExperiementId experimentId) {
		Participant participant = objectFactory
				.createTraceSessionRunParticipant();
		participant.setName(name.toString());
		participant.setExperimentId(experimentId.toString());
		getCurrentRun().getParticipant().add(participant);
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

		getCurrentRun().getCountData().add(countData);

	}

	@Override
	public void addResponseData(
			final UserName participantName, 
			final boolean isCorrect,
			final int responseTime,
			final QuestionType questionType) {
		ResponseData responseData = this.objectFactory
				.createTraceSessionRunResponseData();
		responseData.setParticipant(participantName.toString());
		responseData.setCorrect(isCorrect);
		responseData.setResponsetime(new Integer(responseTime));
		responseData.setQuestionType(questionType.name());

		getCurrentRun().getResponseData().add(responseData);

	}

	@Override
	public void save() {
		JFileChooser fc=new JFileChooser();
		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		int state=fc.showSaveDialog(null);
		if(JFileChooser.APPROVE_OPTION==state){
			JAXB.marshal(getCurrentTrace(), fc.getSelectedFile());
		}
		
				
	}
	
	@Override
	public void export(){
		exporter.export(this);
	}
	
	public JAXBTraceModelImpl with(IExporter anExporter){
		exporter=anExporter;
		return this;
	}
	
}
