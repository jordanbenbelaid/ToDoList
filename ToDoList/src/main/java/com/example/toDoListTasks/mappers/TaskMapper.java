package com.example.toDoListTasks.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.toDoListTasks.data.model.Task;
import com.example.toDoListTasks.dto.TaskDTO;

@Component
public class TaskMapper {

	private ModelMapper modelMapper;
	
	@Autowired
	public TaskMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public Task mapToTask(TaskDTO taskDTO) {
		return this.modelMapper.map(taskDTO, Task.class);
	}
	
	public TaskDTO mapToTaskDTO(Task task) {
		return this.modelMapper.map(task, TaskDTO.class);
	}
}
