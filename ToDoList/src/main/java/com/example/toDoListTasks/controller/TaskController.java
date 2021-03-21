package com.example.toDoListTasks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.toDoListTasks.data.model.Task;
import com.example.toDoListTasks.dto.TaskDTO;
import com.example.toDoListTasks.service.TaskService;

@RestController
@RequestMapping(path = "/task")
@CrossOrigin
public class TaskController {

	private TaskService taskService;

	@Autowired
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	//Getting all tasks
	@GetMapping
	public ResponseEntity<List<TaskDTO>> getAllTasks(){
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Location", "http://localhost:8080/task/all");
		
		List<TaskDTO> data = taskService.readAllTasks();
		return new ResponseEntity<List<TaskDTO>>(data, httpHeaders, HttpStatus.OK);
	}
	
//	//Getting a specific task
//	@GetMapping("/{id}")
//	public ResponseEntity<TaskDTO> getTaskById(@PathVariable("id") int id) {
//		TaskDTO task = taskService.readTaskById(id);
//		return new ResponseEntity<TaskDTO>(task, HttpStatus.OK);
//	}
	
	@PostMapping
	public ResponseEntity<TaskDTO> createTask(@RequestBody Task task){
		TaskDTO newTask = taskService.createTask(task);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(newTask.getId()));
		
		return new ResponseEntity<TaskDTO>(newTask, headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable("id") int id, @RequestBody Task task)
	{
		TaskDTO updateTask = taskService.updateTask(id, task);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(updateTask.getId()));
		
		return new ResponseEntity<TaskDTO>(updateTask, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> deleteTask(@PathVariable("id") int id){
		taskService.deleteTask(id);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
