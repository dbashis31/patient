package com.patient.exception;

import java.io.IOException;

public class NullParamSecurityException extends IOException {
    /**
	 * 
	 */
	String messageKey = null;
	private static final long serialVersionUID = 1L;

	public NullParamSecurityException(String messageKey,String message) {
		super(message);
		this.messageKey=messageKey;
	}
	
	public NullParamSecurityException(String message) {
		super(message);		
	}

	
	public NullParamSecurityException(String messageKey,String message, Throwable cause) {
		super(message,cause);
		this.messageKey=messageKey;
	}

	public String getMessageKey() {
		return messageKey;
	}

}
