package net.oemig.scta.model.exporter.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;

import net.oemig.scta.model.IParticipant;
import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.IRun;
import net.oemig.scta.model.ISession;
import net.oemig.scta.model.ITraceModel;
import net.oemig.scta.model.data.ExperiementId;
import net.oemig.scta.model.data.QuestionType;
import net.oemig.scta.model.exporter.IExporter;

public final class CsvExporterImpl implements IExporter {

	public static CsvExporterImpl getInstance(){
		return new CsvExporterImpl();
	}

	private CsvExporterImpl(){
	}
	
	
	@Override
	public void export(ITraceModel aModel) {
		
		
		try {
			JFileChooser fc=new JFileChooser();
			fc.setDialogType(JFileChooser.SAVE_DIALOG);
			int state=fc.showSaveDialog(null);
			if(JFileChooser.APPROVE_OPTION==state){
				
			
			FileWriter fw = new FileWriter(fc.getSelectedFile());
			
			for(ISession s:aModel.getCurrentTrace().getSessions()){
				for (IRun r:s.getRuns()){
					Map<String, IParticipant> pMap=new HashMap<String, IParticipant>();
					for(IParticipant p: r.getParticipants()){
						pMap.put(p.getName(), p);
					}
					
					for(IResponseData rd:r.getResponseData()){
						writeLine(fw, aModel.getCurrentTrace().getName(),
											s.getName(),
											"runname",
											rd.getParticipantName(), 
											pMap.get(rd.getParticipantName()).getExperimentId(),
											rd.isCorrect(), 
											rd.getResponseTime().intValue(),
											rd.getQuestionType());
					}
				}
			}
			fw.flush();
			fw.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
		
	}
	
	private void writeLine(FileWriter fw, 
							String traceName,
							String sessionName,
							String runName,
							String name,
							ExperiementId experiementId,
							boolean correct, 
							int time,
							QuestionType questionType
							) throws IOException{
		fw.append(traceName).append(',').
			append(sessionName).append(',').
			append(runName).append(',').
			append(name).append(',').
			append(experiementId.toString()).append(',').
			append(Boolean.toString(correct)).append(',').
			append(Integer.toString(time)).append(',').
			append(questionType.toString()).append('\n');
		
	}

}
