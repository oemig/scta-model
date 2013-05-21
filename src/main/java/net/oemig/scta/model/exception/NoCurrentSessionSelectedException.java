package net.oemig.scta.model.exception;

public class NoCurrentSessionSelectedException extends SctaModelException{

	private static final long serialVersionUID = 7653114216410409710L;

	public NoCurrentSessionSelectedException(String msg) {
		super(msg);
	}

}
