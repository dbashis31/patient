package com.patient.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class PatientResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		logger.debug(sw.toString());
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false), null);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(NotFoundException ex,
			WebRequest request) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		logger.debug(sw.toString());
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false), ex.getMessageKey());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CriticalException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(CriticalException ex,
			WebRequest request) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		logger.debug(sw.toString());
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false), null);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(CpException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(CpException ex, WebRequest request) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		logger.debug(sw.toString());
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false), ex.getMessageKey());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NullParamSecurityException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(NullParamSecurityException ex, WebRequest request) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		logger.debug(sw.toString());
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false), ex.getMessageKey());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Validation Failed",
				ex.getBindingResult().toString(), null);
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}