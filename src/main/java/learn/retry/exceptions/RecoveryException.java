package learn.retry.exceptions;

public class RecoveryException extends Exception {

	private static final long serialVersionUID = -2624860630929461300L;

	public RecoveryException(String msg) {
		super(msg);
	}
}
