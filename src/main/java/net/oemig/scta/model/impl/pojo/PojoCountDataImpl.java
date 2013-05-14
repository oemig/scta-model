package net.oemig.scta.model.impl.pojo;

import net.oemig.scta.model.ICountData;
import net.oemig.scta.model.data.UserName;

public final class PojoCountDataImpl implements ICountData {
	public static ICountData of(String letter, int quantity, UserName participantName){
		return new PojoCountDataImpl(letter, quantity, participantName);
	}

	private String letter;
	private int quantity;
	private UserName participantName;
	
	//private constructor
	private PojoCountDataImpl(String letter, int quantity, UserName participantName){
		this.letter=letter;
		this.quantity=quantity;
		this.participantName=participantName;
	}
	@Override
	public String getLetter() {
		return letter;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public UserName getParticipant() {
		return participantName;
	}

}
