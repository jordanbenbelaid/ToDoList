package com.example.toDoListTasks.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,
				reason = "There was no urgency with this ID available!")

public class UrgencyNotFoundException extends EntityNotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8282784259689224091L;

	public UrgencyNotFoundException() {
		super();
	}
	
	public UrgencyNotFoundException(String message) {
		super(message);
	}
}
