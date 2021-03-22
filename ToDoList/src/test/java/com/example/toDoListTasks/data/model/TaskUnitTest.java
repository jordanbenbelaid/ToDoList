package com.example.toDoListTasks.data.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import com.example.toDoListTasks.data.model.Task;
import com.example.toDoListTasks.data.model.Urgency;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TaskUnitTest {

	@Test
	void taskEqualsTest() {
		EqualsVerifier.simple().forClass(Task.class).withPrefabValues(Urgency.class, new Urgency(), new Urgency("Urgent")).verify();
	}

	@Test
	void taskDefaultConstructorTest() {
		Task task = new Task();

		assertNotNull(task);
	}
	
	@Test
	void toStringTest() {
		Task task = new Task();
		Urgency urgency = new Urgency();
		
		task.setUrgency(urgency);
		
		String result = task.toString();
		String expected = "Task [id=" + 0 + ", difficulty=" + null + ", length=" + null + ", name=" + null
				+ ", description=" + null + ", urgency=" + urgency + "]";
		
		assertEquals(expected, result);
	}

	@Test
	void taskAllArgsConstructorTest() {
		Urgency urgency = new Urgency();
		Task task = new Task(1, "hard", "10", "Run", "I go for a run", urgency);

		assertNotNull(task.getId());
		assertNotNull(task.getDifficulty());
		assertNotNull(task.getLength());
		assertNotNull(task.getName());
		assertNotNull(task.getDescription());
		assertNotNull(task.getUrgency());

		assertEquals(1, task.getId());
		assertEquals("hard", task.getDifficulty());
		assertEquals("10", task.getLength());
		assertEquals("Run", task.getName());
		assertEquals("I go for a run", task.getDescription());
		assertEquals(urgency, task.getUrgency());
	}

	@Test
	void taskFiveArgsConstructor() {
		Task task = new Task(1, "hard", "10", "Run", "I go for a run");

		assertNotNull(task.getId());
		assertNotNull(task.getDifficulty());
		assertNotNull(task.getLength());
		assertNotNull(task.getName());
		assertNotNull(task.getDescription());

		assertEquals(1, task.getId());
		assertEquals("hard", task.getDifficulty());
		assertEquals("10", task.getLength());
		assertEquals("Run", task.getName());
		assertEquals("I go for a run", task.getDescription());
	}

	@Test
	void taskNoIdArgConstructor() {
		Task task = new Task("hard", "10", "Run", "I go for a run");

		assertNotNull(task.getDifficulty());
		assertNotNull(task.getLength());
		assertNotNull(task.getName());
		assertNotNull(task.getDescription());

		assertEquals("hard", task.getDifficulty());
		assertEquals("10", task.getLength());
		assertEquals("Run", task.getName());
		assertEquals("I go for a run", task.getDescription());
	}
	
	@Test
	void taskSetTest() {
		Task task = new Task();
		Urgency urgency = new Urgency();
		
		task.setId(1);
		task.setDifficulty("hard");
		task.setLength("10");
		task.setName("Run");
		task.setDescription("Running can be fun");
		task.setUrgency(urgency);
		
		assertEquals(1, task.getId());
		assertEquals("hard", task.getDifficulty());
		assertEquals("10", task.getLength());
		assertEquals("Run", task.getName());
		assertEquals("Running can be fun", task.getDescription());
		assertEquals(urgency, task.getUrgency());
	}
}
