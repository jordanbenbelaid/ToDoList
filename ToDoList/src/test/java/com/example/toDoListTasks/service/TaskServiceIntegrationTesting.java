package com.example.toDoListTasks.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.toDoListTasks.data.model.Task;
import com.example.toDoListTasks.data.model.Urgency;
import com.example.toDoListTasks.data.repository.TaskRepository;
import com.example.toDoListTasks.dto.TaskDTO;
import com.example.toDoListTasks.mappers.TaskMapper;

@SpringBootTest
public class TaskServiceIntegrationTesting {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskMapper taskMapper;
	
	private List<Task> tasks;
	private List<TaskDTO> taskDTOs;
	
	private Task validTask;
	private TaskDTO validTaskDTO;
	
	@BeforeEach
	public void init() {
		Urgency urgency = new Urgency();
		validTask = new Task("Run", "10", "hard", "I go for a run");
		
		tasks = new ArrayList<Task>();
		taskDTOs = new ArrayList<TaskDTO>();
		
		taskRepository.deleteAll();
		
		validTask = taskRepository.save(validTask);
		validTaskDTO = taskMapper.mapToTaskDTO(validTask);
		
		tasks.add(validTask);
		taskDTOs.add(validTaskDTO);
	}
	
	@Test
	void readAllTasksTest() {
		List<TaskDTO> tasksInDb = taskService.readAllTasks();
		
		assertThat(taskDTOs).isEqualTo(tasksInDb);
	}
	
	@Test
	void createTaskTest() {
		Task newTask = new Task("Hide", "9", "easy", "I am Hiding");
		TaskDTO expectedTaskDTO = taskMapper.mapToTaskDTO(newTask);
		
		TaskDTO savedTask = taskService.createTask(newTask);
		expectedTaskDTO.setId(savedTask.getId());
		
		assertThat(savedTask).isEqualTo(expectedTaskDTO);
	}
	
	@Test
	void updateTaskTest() {
		Task updatedTask = new Task(validTask.getId(), "hard", "5", "Run", "I run");
		TaskDTO result = taskService.updateTask(validTask.getId(), updatedTask);
		
		TaskDTO expectedTask = new TaskDTO(validTask.getId(), "Run", "5", "hard", "I run");
		
		assertThat(expectedTask).isEqualTo(result);
	}
	
	@Test
	void deleteTaskTest() {
		assertThat(taskService.deleteTask(validTask.getId())).isEqualTo(true);
	}
	
	
	
	
}
