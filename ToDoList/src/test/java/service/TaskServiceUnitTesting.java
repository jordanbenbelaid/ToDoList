package service;

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
import com.example.toDoListTasks.data.repository.TaskRepository;
import com.example.toDoListTasks.dto.TaskDTO;
import com.example.toDoListTasks.mappers.TaskMapper;
import com.example.toDoListTasks.service.TaskService;

@ExtendWith(MockitoExtension.class)
public class TaskServiceUnitTesting {

	@InjectMocks
	private TaskService taskService;
	
	@Mock
	private TaskRepository taskRepo;
	
	@Mock
	private TaskMapper taskMapper;
	
	private List<Task> tasks;
	private List<TaskDTO> tasksDTO;
	
	private Task validTask;
	private TaskDTO validTaskDTO;
	
	@BeforeEach
	public void init() {
		Urgency urgency = new Urgency();
		validTask = new Task(1, "Run", "10", "hard", "I go for a run", urgency);
		validTaskDTO = new TaskDTO(1, "Run", "10", "hard", "I go for a run");
		
		tasks = new ArrayList<Task>();
		tasksDTO = new ArrayList<TaskDTO>();
		
		tasks.add(validTask);
		tasksDTO.add(validTaskDTO);
	}
	
	@Test
	public void readAllTasksTest() {
		//when specific method is called, we specify what to return
		when(taskRepo.findAll()).thenReturn(tasks);
		when(taskMapper.mapToTaskDTO(validTask)).thenReturn(validTaskDTO);
		
		assertThat(tasksDTO).isEqualTo(taskService.readAllTasks());
		
		verify(taskRepo, times(1)).findAll();
		verify(taskMapper, times(1)).mapToTaskDTO(validTask);
	}
	
	@Test
	public void createTaskTest() {
		when(taskRepo.save(validTask)).thenReturn(validTask);
		when(taskMapper.mapToTaskDTO(validTask)).thenReturn(validTaskDTO);
		
		assertThat(validTaskDTO).isEqualTo(taskService.createTask(validTask));
		
		verify(taskRepo, times(1)).save(validTask);
		verify(taskMapper, times(1)).mapToTaskDTO(validTask);
	}
	
	@Test
	public void updateTaskTest() {
		Urgency urgency = new Urgency();
		Task updatedTask = new Task(1, "Run", "10", "hard", "I go for a run", urgency);
		TaskDTO updatedTaskDTO = new TaskDTO(1, "Run", "10", "hard", "I go for a run");
		
		when(taskRepo.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(validTask));
		
		when(taskRepo.save(Mockito.any(Task.class))).thenReturn(updatedTask);
		
		when(taskMapper.mapToTaskDTO(Mockito.any(Task.class))).thenReturn(updatedTaskDTO);
		
		TaskDTO testDTO = taskService.updateTask(validTask.getId(), updatedTask);
		
		assertThat(updatedTaskDTO).isEqualTo(testDTO);
	}
	
	@Test
	public void deleteTaskTest() {
		when(taskRepo.existsById(Mockito.any(Integer.class))).thenReturn(true).thenReturn(false);
		
		assertThat(true).isEqualTo(taskService.deleteTask(1));
		
		verify(taskRepo, times(2)).existsById(Mockito.any(Integer.class));
		verify(taskRepo, times(1)).deleteById(Mockito.any(Integer.class));
		
	}
}
