package com.patient.exception;

public class CriticalException extends Exception {

	final String messageKey;
	
	
	private static final long serialVersionUID = -6947140709460374908L;

	

	public CriticalException(String messageKey,String message) {
		super(message);
		this.messageKey=messageKey;
	}

	
	public CriticalException(String messageKey,String message, Throwable cause) {
		super(message,cause);
		this.messageKey=messageKey;
	}
	
	public String getMessageKey() {
		return messageKey;
	}

}