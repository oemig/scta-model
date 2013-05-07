package net.oemig.scta.model.impl.jaxb;

import java.util.ArrayList;
import java.util.List;

import net.oemig.scta.model.ICountData;
import net.oemig.scta.model.IParticipant;
import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.IRun;
import net.oemig.scta.model.binding.Trace.Session.Run;
import net.oemig.scta.model.binding.Trace.Session.Run.CountData;
import net.oemig.scta.model.binding.Trace.Session.Run.Participant;
import net.oemig.scta.model.binding.Trace.Session.Run.ResponseData;

public final class JAXBRunImpl implements IRun {
	
	public static JAXBRunImpl of(Run aRun){
		return new JAXBRunImpl(aRun);
	}

	private Run run;
	
	private JAXBRunImpl(Run aRun){
		this.run=aRun;
	}

	@Override
	public List<ICountData> getCountData() {
		List<ICountData> result=new ArrayList<ICountData>();
		for(CountData c:run.getCountData()){
			result.add(JAXBCountDataImpl.of(c));
		}
		return result;
	}

	@Override
	public List<IResponseData> getResponseData() {
		List<IResponseData> result=new ArrayList<IResponseData>();
		for(ResponseData r:run.getResponseData()){
			result.add(JAXBResponseData.of(r));
		}
		return result;
	}
	
	@Override
	public List<IParticipant> getParticipants(){
		List<IParticipant> result=new ArrayList<IParticipant>();
		for(Participant p:run.getParticipant()){
			result.add(JAXBParticipantImpl.of(p));
		}
		return result;
	}

}
