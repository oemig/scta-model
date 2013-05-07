package net.oemig.scta.model.exception;

/**
 * Exception indicating missing response data in a run. 
 * @author chris
 */
public class ResponseDataMissingException extends SctaModelException {

	private static final long serialVersionUID = 2766033478173575050L;

	public ResponseDataMissingException(String msg) {
		super(msg);
	}

}
