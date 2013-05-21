package net.oemig.scta.model.impl.jaxb;

import net.oemig.scta.model.ICountData;
import net.oemig.scta.model.binding.ObjectFactory;
import net.oemig.scta.model.binding.Trace.Session.Run.CountData;
import net.oemig.scta.model.data.UserName;

public final class JAXBCountDataImpl implements ICountData {

	private CountData countData;
	
	private JAXBCountDataImpl(){
		this.countData=new ObjectFactory().createTraceSessionRunCountData();
	}

	private void setCountData(CountData aCountData){
		countData=aCountData;
	}
	
	@Override
	public String getLetter() {
		return countData.getLetter();
	}

	@Override
	public UserName getParticipant() {
		return UserName.of(countData.getParticipant());
	}

	@Override
	public int getQuantity() {
		return countData.getQuantity().intValue();
	}
	
	public static Builder builder(){
		return new Builder();
	}
	
	public static class Builder{
		private JAXBCountDataImpl c=new JAXBCountDataImpl();
		
		public Builder countData(CountData aCountData){
			c.setCountData(aCountData);
			return this;
		}
		public ICountData build(){
			return c;
		}
		
	}
}
