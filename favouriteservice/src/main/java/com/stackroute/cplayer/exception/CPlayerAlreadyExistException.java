package com.stackroute.cplayer.exception;

public class CPlayerAlreadyExistException extends Exception {
	
	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param message
	 */
	public CPlayerAlreadyExistException(final String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "CPlayerAlreadyExistException [message=" + message + "]";
	}

}
