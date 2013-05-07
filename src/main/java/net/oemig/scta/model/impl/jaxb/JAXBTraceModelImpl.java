package net.oemig.scta.model.impl.jaxb;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.data.UserName;
import net.oemig.scta.model.exporter.IExporter;

public final class JAXBTraceModelImpl implements ITraceModel {

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
			currentTrace=JAXB.unmarshal(traceFile, Trace.class);
		}catch(Exception e){
			//not found.. create a new one
			currentTrace=this.objectFactory.createTrace();
			currentTrace.setName(traceName);
		}

		String sessionName = JOptionPane
				.showInputDialog("Please enter a session name!");

		currentSession=this.objectFactory.createTraceSession();
		currentSession.setName(sessionName);
		currentTrace.getSession().add(currentSession);

		currentRun=this.objectFactory.createTraceSessionRun();
		currentSession.getRun().add(currentRun);

	}

	/* (non-Javadoc)
	 * @see net.oemig.scta.tracer.model.ITraceModel#getCurrentTrace()
	 */
	@Override
	public ITrace getCurrentTrace() {
		return JAXBTraceImpl.of(currentTrace);
	}

	@Override
	public ISession getCurrentSession() {
		return JAXBSessionImpl.of(currentSession);
	}


	@Override
	public IRun getCurrentRun() {
		return JAXBRunImpl.of(currentRun);
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
			final int responseTime,
			final QuestionType questionType) {
		ResponseData responseData = this.objectFactory
				.createTraceSessionRunResponseData();
		responseData.setParticipant(participantName.toString());
		responseData.setCorrect(isCorrect);
		responseData.setResponsetime(Integer.valueOf(responseTime));
		responseData.setQuestionType(questionType.name());

		currentRun.getResponseData().add(responseData);

	}

	@Override
	public void save() {
		JFileChooser fc=new JFileChooser();
		fc.setDialogType(JFileChooser.SAVE_DIALOG);
		int state=fc.showSaveDialog(null);
		if(JFileChooser.APPROVE_OPTION==state){
			JAXB.marshal(currentTrace, fc.getSelectedFile());
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
