package com.example.toDoListTasks.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,
				reason = "There was no task with this ID available!")
public class TaskNotFoundException extends EntityNotFoundException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2095798436705012838L;

	public TaskNotFoundException() {
		super();
	}
	
	public TaskNotFoundException(String message) {
		super(message);
	}
	
}
