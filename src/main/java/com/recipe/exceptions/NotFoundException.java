package com.recipe.exceptions;

public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String source, int id) {
		super(source+" ID : "+id+" not found");
	}
	
	public NotFoundException(String source) {
		super(source+" not found");
	}
}
