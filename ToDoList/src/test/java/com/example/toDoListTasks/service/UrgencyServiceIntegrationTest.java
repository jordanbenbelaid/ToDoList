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
import com.example.toDoListTasks.data.repository.UrgencyRepository;
import com.example.toDoListTasks.dto.TaskDTO;
import com.example.toDoListTasks.dto.UrgencyDTO;
import com.example.toDoListTasks.mappers.UrgencyMapper;

@SpringBootTest
public class UrgencyServiceIntegrationTest {

	@Autowired
	private UrgencyService urgencyService;
	
	@Autowired
	private UrgencyRepository urgencyRepo;
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UrgencyMapper urgencyMapper;
	
	private List<Urgency> urgencies;
	private List<UrgencyDTO> urgencyDTOs;
	
	private Urgency validUrgency;
	private UrgencyDTO validUrgencyDTO;
	
	private Task validTask;
	private TaskDTO validTaskDTO;
	
	@BeforeEach
	public void init() {
		validUrgency = new Urgency("List");
		
		validTask = new Task("hard", "10", "Run", "I go for a run");
		validTask.setUrgency(new Urgency(1, "List"));
		
		urgencyRepo.deleteAll();
		urgencyRepo.flush();
		validUrgency = urgencyRepo.saveAndFlush(validUrgency);
		validTask.setUrgency(validUrgency);
		validTask = taskRepo.saveAndFlush(validTask);
		
		validTaskDTO = new TaskDTO(validTask.getId(), "Run", "10", "hard", "I go for a run");
		validUrgencyDTO = new UrgencyDTO(validUrgency.getId(), "List", List.of(validTaskDTO));
		
		urgencies = List.of(validUrgency);
		urgencyDTOs = List.of(validUrgencyDTO);
	}
	
	@Test
	void readAllUrgencyTest() {
		List<UrgencyDTO> urgencyToTest = urgencyService.readAllUrgency();
		//returns wrong order for some reason
		assertThat(urgencyDTOs).isEqualTo(urgencyToTest);
	}
	
	@Test
	void createUrgencyTest() {
		Urgency newUrgency = new Urgency("long list");
		UrgencyDTO expectedUrgencyDTO = urgencyMapper.mapToUrgencyDTO(newUrgency);
		
		UrgencyDTO savedUrgency = urgencyService.createUrgency(newUrgency);
		expectedUrgencyDTO.setId(savedUrgency.getId());
		
		assertThat(savedUrgency).isEqualTo(expectedUrgencyDTO);
	}
	
	@Test
	void updateUrgencyTest() {
		Urgency updatedUrgency = new Urgency(validUrgency.getId(), "updated list");
		UrgencyDTO result = urgencyService.updateUrgency(validUrgency.getId(), updatedUrgency);
		
		List<TaskDTO> tasks = new ArrayList();
		tasks.add(validTaskDTO);
		
		UrgencyDTO expectedUrgency = new UrgencyDTO(validUrgency.getId(), "updated list", tasks);
		
		assertThat(expectedUrgency).isEqualTo(result);
	}
	
	@Test
	void deleteUrgencyTest() {
		assertThat(urgencyService.deleteUrgency(validUrgency.getId())).isEqualTo(true);
	}
	
}
