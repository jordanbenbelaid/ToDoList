package com.example.toDoListTasks.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import com.example.toDoListTasks.data.model.Task;
import com.example.toDoListTasks.data.model.Urgency;
import com.example.toDoListTasks.dto.TaskDTO;
import com.example.toDoListTasks.dto.UrgencyDTO;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TaskDtoUnitTest {

	@Test
	public void taskDtoEqualsTest() {
		EqualsVerifier.simple().forClass(TaskDTO.class).verify();
	}
	
	@Test
	public void toStringTest() {
		TaskDTO task = new TaskDTO();
		
		String result = task.toString();
		String expected = "TaskDTO [id=" + 0 + ", name=" + null + ", length=" + null + ", difficulty=" + null
				+ ", description=" + null + "]";
		
		assertEquals(expected, result);
	}
	
	@Test
	public void taskDtoAllArgsConstructorTest() {
		TaskDTO task = new TaskDTO(1, "Run", "10", "hard", "I go for a run");

		assertNotNull(task.getId());
		assertNotNull(task.getName());
		assertNotNull(task.getLength());
		assertNotNull(task.getDifficulty());
		assertNotNull(task.getDescription());

		assertEquals(1, task.getId());
		assertEquals("Run", task.getName());
		assertEquals("10", task.getLength());
		assertEquals("hard", task.getDifficulty());
		assertEquals("I go for a run", task.getDescription());
	}
	
	@Test
	public void setTaskDtoTest() {
		TaskDTO task = new TaskDTO();
		
		task.setId(1);
		task.setDifficulty("hard");
		task.setLength("10");
		task.setName("Run");
		task.setDescription("Running can be fun");
		
		assertEquals(1, task.getId());
		assertEquals("hard", task.getDifficulty());
		assertEquals("10", task.getLength());
		assertEquals("Run", task.getName());
		assertEquals("Running can be fun", task.getDescription());
	}
}
