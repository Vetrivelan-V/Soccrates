package com.soccrates.middletier.util;

public class SoccratesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The message. */
	String message;

	/** The exception id. */
	int exceptionId;

	/**
	 * Gets the exception id.
	 *
	 * @return the exception id
	 */
	public int getExceptionId() {
		return exceptionId;
	}
	

	/**
	 * Sets the exception id.
	 *
	 * @param exceptionId
	 *            the new exception id
	 */
	public void setExceptionId(int exceptionId) {
		this.exceptionId = exceptionId;
	}

 

	public SoccratesException(int exceptionCode) {
		// TODO Auto-generated constructor stub
		 this.setExceptionId(exceptionCode);
		 this.setMessage("Please provide Execption Code to admin  Error Messages not set");;

	}

 
	public SoccratesException(String message, int id) {
		// TODO Auto-generated constructor stub
		this.message = message;
		this.setExceptionId(id);
	}

	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message
	 *            the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
