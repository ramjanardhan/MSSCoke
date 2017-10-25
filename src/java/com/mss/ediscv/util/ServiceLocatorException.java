/*
 ******************************************************************************
 *
 * Project : Miracle Supply Chain Visibility portal v2.0
 *
 * Package : com.mss.mscvp.utill
 *
 * Date    : Mar 11, 2013 1:38:00 PM
 *
 * Author  : Nagireddy Seerapu <nseerapu@miraclesoft.com>
 *
 * File    : ServiceLocatorException.java
 *

 * *****************************************************************************
 */
package com.mss.ediscv.util;

public class ServiceLocatorException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ServiceLocatorException() {
		super();

	}

	/**
	 * @param message
	 */
	public ServiceLocatorException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceLocatorException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ServiceLocatorException(Throwable cause) {
		super(cause);
	}
}
