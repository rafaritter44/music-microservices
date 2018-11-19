package com.github.ilegra.final_project.playlist_service.exception;


public class InvalidIdException extends Exception {

	private static final long serialVersionUID = -3517170452748690517L;

	public InvalidIdException(String error) {
		super(error);
	}
}