/**
 * 
 */
package com.example.demo.exceptions;

/**
 * @author IsraelatHeart
 *
 */
public class CustomerNotFoundExceptin extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public CustomerNotFoundExceptin() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public CustomerNotFoundExceptin(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public CustomerNotFoundExceptin(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public CustomerNotFoundExceptin(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CustomerNotFoundExceptin(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
