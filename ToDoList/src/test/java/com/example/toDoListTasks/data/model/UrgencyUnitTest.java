package com.example.toDoListTasks.data.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.example.toDoListTasks.data.model.Task;
import com.example.toDoListTasks.data.model.Urgency;

import nl.jqno.equalsverifier.EqualsVerifier;

public class UrgencyUnitTest {

	@Test
	public void urgencyEqualsTest() {
		EqualsVerifier.simple().forClass(Urgency.class)
				.withPrefabValues(Task.class, new Task("hard", "10", "Run", "I go for a run"), new Task()).verify();
	}

	@Test
	public void urgencyDefaultConstructorTest() {
		Urgency urgency = new Urgency();

		assertNotNull(urgency);
	}

	@Test
	public void toStringTest() {
		Urgency urgency = new Urgency();

		String result = urgency.toString();
		String expected = "Urgency [id=" + 0 + ", name=" + null + ", tasks=" + null + "]";

		assertEquals(expected, result);
	}

	@Test
	public void urgencyAllArgsConstructorTest() {
		Urgency urgency = new Urgency();

		urgency.setId(1);
		urgency.setName("List");
		urgency.setTasks(new ArrayList());

		assertNotNull(urgency.getId());
		assertNotNull(urgency.getName());
		assertNotNull(urgency.getTasks());

		assertEquals(1, urgency.getId());
		assertEquals("List", urgency.getName());
		assertEquals(new ArrayList(), urgency.getTasks());
	}

	@Test
	public void urgencyNameConstructorTest() {
		Urgency urgency = new Urgency("Name");

		assertNotNull(urgency.getName());

		assertEquals("Name", urgency.getName());
	}

	@Test
	public void urgencyIdNameConstructorTest() {
		Urgency urgency = new Urgency(1, "Name");

		assertNotNull(urgency.getId());
		assertNotNull(urgency.getName());

		assertEquals(1, urgency.getId());
		assertEquals("Name", urgency.getName());
	}
}
