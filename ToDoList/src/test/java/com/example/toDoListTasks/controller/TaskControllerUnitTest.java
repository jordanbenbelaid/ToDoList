package com.example.toDoListTasks.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.toDoListTasks.data.model.Task;
import com.example.toDoListTasks.data.model.Urgency;
import com.example.toDoListTasks.dto.TaskDTO;
import com.example.toDoListTasks.service.TaskService;

@WebMvcTest(TaskController.class)
public class TaskControllerUnitTest {

	@Autowired
	private TaskController taskController;

	@MockBean
	private TaskService taskService;

	private List<Task> tasks;
	private List<TaskDTO> taskDTOs;

	private Task validTask;
	private TaskDTO validTaskDTO;

	@BeforeEach
	public void init() {
		Urgency urgency = new Urgency();
		validTask = new Task(1, "hard", "10", "Run", "I go for a run", urgency);
		validTaskDTO = new TaskDTO(1, "hard", "10", "Run", "I go for a run");

		tasks = new ArrayList<Task>();
		taskDTOs = new ArrayList<TaskDTO>();

		tasks.add(validTask);
		taskDTOs.add(validTaskDTO);
	}

	@Test
	public void readAllTasksTest() {
		when(taskService.readAllTasks()).thenReturn(taskDTOs);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "http://localhost:8080/task/all");

		ResponseEntity<List<TaskDTO>> response = new ResponseEntity<List<TaskDTO>>(taskDTOs, headers, HttpStatus.OK);

		assertThat(response).isEqualTo(taskController.getAllTasks());

		verify(taskService, times(1)).readAllTasks();
	}

	@Test
	public void createTaskTest() {
		when(taskService.createTask(validTask)).thenReturn(validTaskDTO);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(validTaskDTO.getId()));

		ResponseEntity<TaskDTO> response = new ResponseEntity<TaskDTO>(validTaskDTO, headers, HttpStatus.CREATED);

		assertThat(response).isEqualTo(taskController.createTask(validTask));

		verify(taskService, times(1)).createTask(validTask);
	}
	
	@Test
	public void updateTaskTest() {
		when(taskService.updateTask(validTask.getId(), validTask)).thenReturn(validTaskDTO);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(validTaskDTO.getId()));
		
		ResponseEntity<TaskDTO> response = new ResponseEntity<TaskDTO>(validTaskDTO, headers, HttpStatus.OK);
		
		assertThat(response).isEqualTo(taskController.updateTask(validTask.getId(), validTask));
		
		verify(taskService, times(1)).updateTask(validTask.getId(), validTask);
	}
	
	@Test
	public void deleteTaskTest() {
		when(taskService.deleteTask(validTask.getId())).thenReturn(true);
		
		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
		assertThat(response).isEqualTo(taskController.deleteTask(validTask.getId()));
		
		verify(taskService, times(1)).deleteTask(validTask.getId());
	}

}
