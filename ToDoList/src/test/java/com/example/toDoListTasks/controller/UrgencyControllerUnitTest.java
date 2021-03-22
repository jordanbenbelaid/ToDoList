package com.example.toDoListTasks.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.toDoListTasks.data.model.Task;
import com.example.toDoListTasks.data.model.Urgency;
import com.example.toDoListTasks.dto.TaskDTO;
import com.example.toDoListTasks.dto.UrgencyDTO;
import com.example.toDoListTasks.service.UrgencyService;

@WebMvcTest(UrgencyController.class)
public class UrgencyControllerUnitTest {

	@Autowired
	private UrgencyController urgencyController;

	@MockBean
	private UrgencyService urgencyService;

	private List<Urgency> urgencies;
	private List<UrgencyDTO> urgencyDTOs;

	private Urgency validUrgency;
	private UrgencyDTO validUrgencyDTO;

	private Task validTask;
	private TaskDTO validTaskDTO;

	@BeforeEach
	public void init() {
		validUrgency = new Urgency(1, "List");

		validTask = new Task(1, "hard", "10", "Run", "I go for a run");
		validTaskDTO = new TaskDTO(1, "hard", "10", "Run", "I go for a run");

		validUrgencyDTO = new UrgencyDTO(1, "List", List.of(validTaskDTO));

		urgencies = List.of(validUrgency);
		urgencyDTOs = List.of(validUrgencyDTO);
	}

	@Test
	void readAllUrgenciesTest() {
		when(urgencyService.readAllUrgency()).thenReturn(urgencyDTOs);

		ResponseEntity<List<UrgencyDTO>> response = new ResponseEntity<List<UrgencyDTO>>(urgencyDTOs, HttpStatus.OK);

		assertThat(response).isEqualTo(urgencyController.getAllUrgencies());

		verify(urgencyService, times(1)).readAllUrgency();
	}

	@Test
	void createUrgencyTest() {
		when(urgencyService.createUrgency(validUrgency)).thenReturn(validUrgencyDTO);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(validUrgencyDTO.getId()));

		ResponseEntity<UrgencyDTO> response = new ResponseEntity<UrgencyDTO>(validUrgencyDTO, headers,
				HttpStatus.CREATED);

		assertThat(response).isEqualTo(urgencyController.createUrgency(validUrgency));

		verify(urgencyService, times(1)).createUrgency(validUrgency);
	}

	@Test
	void updateUrgencyTest() {
		when(urgencyService.updateUrgency(validUrgency.getId(), validUrgency)).thenReturn(validUrgencyDTO);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(validUrgencyDTO.getId()));

		ResponseEntity<UrgencyDTO> response = new ResponseEntity<UrgencyDTO>(validUrgencyDTO, headers,
				HttpStatus.OK);

		assertThat(response).isEqualTo(urgencyController.updateUrgency(validUrgency.getId(), validUrgency));

		verify(urgencyService, times(1)).updateUrgency(validUrgency.getId(), validUrgency);
	}

	@Test
	void deleteUrgencyTest() {
	when(urgencyService.deleteUrgency(validUrgency.getId())).thenReturn(true);
	
	ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(true, HttpStatus.OK);
	
	assertThat(response).isEqualTo(urgencyController.deleteUrgency(validUrgency.getId()));
	
	verify(urgencyService, times(1)).deleteUrgency(validUrgency.getId());
	}
}
