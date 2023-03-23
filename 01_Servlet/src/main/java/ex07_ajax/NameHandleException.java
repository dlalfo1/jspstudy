package ex07_ajax;

public class NameHandleException extends MyHandleException{

	private static final long serialVersionUID = 1L;
	
	public NameHandleException(String message, int errorCode) {
		super(message, errorCode);
	}
}
