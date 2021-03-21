package com.example.toDoListTasks.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.toDoListTasks.data.model.Task;
import com.example.toDoListTasks.data.model.Urgency;
import com.example.toDoListTasks.data.repository.UrgencyRepository;
import com.example.toDoListTasks.dto.TaskDTO;
import com.example.toDoListTasks.dto.UrgencyDTO;
import com.example.toDoListTasks.mappers.UrgencyMapper;
import com.example.toDoListTasks.service.UrgencyService;

@ExtendWith(MockitoExtension.class)
public class UrgencyServiceUnitTesting {

	@InjectMocks
	private UrgencyService urgencyService;
	
	@Mock
	private UrgencyRepository urgencyRepo;
	
	@Mock
	private UrgencyMapper urgencyMapper;
	
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
	public void readAllUrgencyTest() {
		when(urgencyRepo.findAll()).thenReturn(urgencies);
		when(urgencyMapper.mapToUrgencyDTO(Mockito.any(Urgency.class))).thenReturn(validUrgencyDTO);
		
		assertThat(urgencyDTOs).isEqualTo(urgencyService.readAllUrgency());
		
		verify(urgencyRepo, times(1)).findAll();
		verify(urgencyMapper, times(1)).mapToUrgencyDTO(Mockito.any(Urgency.class));
	}
	
	@Test
	public void createUrgencyTest() {
		when(urgencyRepo.save(Mockito.any(Urgency.class))).thenReturn(validUrgency);
		when(urgencyMapper.mapToUrgencyDTO(Mockito.any(Urgency.class))).thenReturn(validUrgencyDTO);
		
		assertThat(validUrgencyDTO).isEqualTo(urgencyService.createUrgency(validUrgency));
		
		verify(urgencyRepo, times(1)).save(Mockito.any(Urgency.class));
		verify(urgencyMapper, times(1)).mapToUrgencyDTO(Mockito.any(Urgency.class));
	}
	
	@Test
	public void updateUrgencyTest() {
		Urgency updatedUrgency = new Urgency(1, "List");
		UrgencyDTO updatedUrgencyDTO = new UrgencyDTO(1, "List", new ArrayList());
		
		when(urgencyRepo.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(validUrgency));
		
		when(urgencyRepo.save(Mockito.any(Urgency.class))).thenReturn(updatedUrgency);
		
		when(urgencyMapper.mapToUrgencyDTO(Mockito.any(Urgency.class))).thenReturn(updatedUrgencyDTO);
		
		UrgencyDTO testDTO = urgencyService.updateUrgency(validUrgency.getId(), updatedUrgency);
		
		assertThat(updatedUrgencyDTO).isEqualTo(testDTO);
	}
	
	@Test
	public void deleteUrgencyTest() {
		when(urgencyRepo.existsById(Mockito.any(Integer.class))).thenReturn(true).thenReturn(false);
		
		assertThat(true).isEqualTo(urgencyService.deleteUrgency(1));
		
		verify(urgencyRepo, times(2)).existsById(Mockito.any(Integer.class));
		verify(urgencyRepo, times(1)).deleteById(Mockito.any(Integer.class));
	}
	
}
