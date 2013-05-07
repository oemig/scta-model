package net.oemig.scta.model.impl.pojo;

import net.oemig.scta.model.ICountData;

public final class CountDataImpl implements ICountData {
	public static ICountData of(String letter, int quantity, String participantName){
		return new CountDataImpl(letter, quantity, participantName);
	}

	private String letter;
	private int quantity;
	private String participantName;
	
	private CountDataImpl(String letter, int quantity, String participantName){
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
	public String getParticipant() {
		return participantName;
	}

}
