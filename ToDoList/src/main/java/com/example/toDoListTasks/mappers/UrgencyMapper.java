package com.example.toDoListTasks.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.toDoListTasks.data.model.Urgency;
import com.example.toDoListTasks.dto.UrgencyDTO;

@Component
public class UrgencyMapper {

	private ModelMapper modelMapper;
	
	@Autowired
	public UrgencyMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	public Urgency mapToUrgency(UrgencyDTO urgencyDTO) {
		return this.modelMapper.map(urgencyDTO, Urgency.class);
	}
	
	public UrgencyDTO mapToUrgencyDTO(Urgency urgency) {
		return this.modelMapper.map(urgency, UrgencyDTO.class);
	}
}
