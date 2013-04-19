package net.oemig.scta.model.impl;

import net.oemig.scta.model.ICountData;
import net.oemig.scta.model.binding.Trace.Session.Run.CountData;

public class JAXBCountDataImpl implements ICountData {

	public static JAXBCountDataImpl of(CountData aCountData){
		return new JAXBCountDataImpl(aCountData);
	}

	private CountData countData;
	
	private JAXBCountDataImpl(CountData aCountData){
		this.countData=aCountData;
	}

	@Override
	public String getLetter() {
		return countData.getLetter();
	}

	@Override
	public String getParticipant() {
		return countData.getParticipant();
	}

	@Override
	public int getQuantity() {
		return 0;
	}
}
