package com.patient.exception;

public class CpException extends RuntimeException{

	private final String messageKey;
	private static final long serialVersionUID = -6947140709460374908L;
	
    public CpException(String messageKey,String message) {
		super(message);
		this.messageKey=messageKey;
	}

	
	public CpException(String messageKey,String message, Throwable cause) {
		super(message,cause);
		this.messageKey=messageKey;
	}

	public String getMessageKey() {
		return messageKey;
	}
}