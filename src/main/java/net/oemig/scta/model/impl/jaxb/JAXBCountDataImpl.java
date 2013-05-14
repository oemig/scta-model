package net.oemig.scta.model.impl.jaxb;

import net.oemig.scta.model.ICountData;
import net.oemig.scta.model.binding.Trace.Session.Run.CountData;
import net.oemig.scta.model.data.UserName;

public final class JAXBCountDataImpl implements ICountData {

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
	public UserName getParticipant() {
		return UserName.of(countData.getParticipant());
	}

	@Override
	public int getQuantity() {
		return countData.getQuantity().intValue();
	}
}
