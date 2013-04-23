package net.oemig.scta.model.data;

/**
 * The {@link Millisecond} class represents the number
 * of milliseconds as class for type safety.
 * 
 * @author chris
 *
 */
public final class Millisecond {

	public static Millisecond of(int m){
		return new Millisecond(m);
	}

	private int millis;
	
	private Millisecond(int m){
		millis=m;
	}
	
	public void plus(Millisecond m){
		millis=millis+m.intValue();
	}
	
	public int intValue(){
		return millis;
	}
	
	public int getSeconds(){
		return millis/1000;
	}
}
