package com.stackroute.cplayer.exception;

public class CPlayerNotFoundException extends Exception {

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CPlayerNotFoundException(final String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "CPlayerNotFoundException [message=" + message + "]";
	}

}
