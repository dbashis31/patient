package com.patient.exception;
import java.util.Date;

public class ExceptionResponse {
  private Date timestamp;
  private String messageKey;
  private String message;
  private String details;

  public ExceptionResponse(Date timestamp, String message, String details,String messageKey) {
    super();
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
    this.messageKey=messageKey;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public String getDetails() {
    return details;
  }

public String getMessageKey() {
	return messageKey;
}


  
  

}