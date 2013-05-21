package net.oemig.scta.model.impl.jaxb;

import java.util.List;

import net.oemig.scta.model.ICountData;
import net.oemig.scta.model.IParticipant;
import net.oemig.scta.model.IResponseData;
import net.oemig.scta.model.IRun;
import net.oemig.scta.model.binding.ObjectFactory;
import net.oemig.scta.model.binding.Trace.Session.Run;
import net.oemig.scta.model.binding.Trace.Session.Run.CountData;
import net.oemig.scta.model.binding.Trace.Session.Run.Participant;
import net.oemig.scta.model.binding.Trace.Session.Run.ResponseData;

import com.google.common.collect.Lists;

public final class JAXBRunImpl implements IRun {
	
	private Run run;
	
	private JAXBRunImpl(){
		this.run=new ObjectFactory().createTraceSessionRun();//empty run
	}

	private void setRun(Run aRun){
		run=aRun;
	}
	
	@Override
	public List<ICountData> getCountData() {
		List<ICountData> result=Lists.newArrayList();
		for(CountData c:run.getCountData()){
			result.add(JAXBCountDataImpl.builder().countData(c).build());
		}
		return result;
	}

	@Override
	public List<IResponseData> getResponseData() {
		List<IResponseData> result=Lists.newArrayList();
		for(ResponseData r:run.getResponseData()){
			result.add(JAXBResponseData.builder().responseData(r).build());
		}
		return result;
	}
	
	@Override
	public List<IParticipant> getParticipants(){
		List<IParticipant> result=Lists.newArrayList();
		for(Participant p:run.getParticipant()){
			result.add(JAXBParticipantImpl.builder().participant(p).build());
		}
		return result;
	}

	public static Builder builder(){
		return new Builder();
	}
	
	public static class Builder{
		private JAXBRunImpl r=new JAXBRunImpl();
		
		public Builder run(Run aRun){
			r.setRun(aRun);
			return this;
		}
		public IRun build(){
			return r;
		}
		
	}
}
