package net.oemig.scta.model.exception;

/**
 * Top level exception for the scta model. Every exception in
 * this jar is derived from this one.
 * 
 * @author chris
 *
 */
public class SctaModelException extends Exception {

	private static final long serialVersionUID = -3287816507881258828L;
	
	public SctaModelException(String msg) {
		super(msg);
	}

}
