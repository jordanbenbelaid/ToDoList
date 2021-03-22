
package com.example.toDoListTasks.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.toDoListTasks.data.model.Task;
import com.example.toDoListTasks.data.repository.TaskRepository;
import com.example.toDoListTasks.dto.TaskDTO;
import com.example.toDoListTasks.exceptions.TaskNotFoundException;
import com.example.toDoListTasks.mappers.TaskMapper;

@Service
public class TaskService {

	private TaskRepository taskRepository;
	private TaskMapper taskMapper;
	
	@Autowired
	public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
		this.taskMapper = taskMapper;
		this.taskRepository = taskRepository;
	}
	
	public List<TaskDTO> readAllTasks(){
		List<Task> tasks = taskRepository.findAll();
		List<TaskDTO> taskDTOs = new ArrayList<TaskDTO>();
		
		tasks.forEach(task -> taskDTOs.add(taskMapper.mapToTaskDTO(task)));
		
		return taskDTOs;
	}
	
//	public TaskDTO readTaskById(Integer id) {
//		Optional<Task> task = taskRepository.findById(id);
//		
//		if(task.isPresent()) {
//			return taskMapper.mapToTaskDTO(task.get());		
//		} else {
//			throw new TaskNotFoundException();
//		}
//	}
	
	public TaskDTO createTask(Task task) {
		Task newTask = taskRepository.save(task);
		
		return taskMapper.mapToTaskDTO(newTask);
	}
	
	public TaskDTO updateTask(Integer id, Task task) {
		Optional<Task> taskInDbOptional = taskRepository.findById(id);
		Task taskInDb;
		
		if(taskInDbOptional.isPresent()) {
			taskInDb = taskInDbOptional.get();	
		} else {
			throw new TaskNotFoundException();
		}
		
		taskInDb.setName(task.getName());
		taskInDb.setDescription(task.getDescription());
		taskInDb.setDifficulty(task.getDifficulty());
		taskInDb.setLength(task.getLength());
		
		Task updatedTask = taskRepository.save(taskInDb);
		
		return taskMapper.mapToTaskDTO(updatedTask);
	}
	
	public boolean deleteTask(Integer id) {
		if(!taskRepository.existsById(id)) {
			throw new TaskNotFoundException();
		} 
		taskRepository.deleteById(id);
		
		boolean exists = taskRepository.existsById(id);
		
		return !exists;
	}
	
	
}
