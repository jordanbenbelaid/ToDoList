package com.example.toDoListTasks.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.toDoListTasks.data.model.Urgency;
import com.example.toDoListTasks.data.repository.UrgencyRepository;
import com.example.toDoListTasks.dto.UrgencyDTO;
import com.example.toDoListTasks.exceptions.UrgencyNotFoundException;
import com.example.toDoListTasks.mappers.UrgencyMapper;

@Service
public class UrgencyService {

	private UrgencyRepository urgencyRepository;
	private UrgencyMapper urgencyMapper;
	
	@Autowired
	public UrgencyService(UrgencyRepository urgencyRepository, UrgencyMapper urgencyMapper) {
		this.urgencyRepository = urgencyRepository;
		this.urgencyMapper = urgencyMapper;
	}
	
	@Transactional
	public List<UrgencyDTO> readAllUrgency(){
		List<Urgency> urgencyInDb = urgencyRepository.findAll();
		List<UrgencyDTO> urgencyReturns = new ArrayList<UrgencyDTO>();
		
		urgencyInDb.forEach(urgency -> {urgencyReturns
			.add(urgencyMapper.mapToUrgencyDTO(urgency));
		});
		
		return urgencyReturns;
	}
	
	public UrgencyDTO createUrgency(Urgency urgency) {
		Urgency savedUrgency = urgencyRepository.save(urgency);
		
		return urgencyMapper.mapToUrgencyDTO(savedUrgency);
	}
	
	public UrgencyDTO updateUrgency(Integer id, Urgency urgency) {		
		Optional<Urgency> urgencyOpt = urgencyRepository.findById(id);
		Urgency urgencyInDb = urgencyOpt.orElseThrow(() -> {
			throw new UrgencyNotFoundException();		
		});
		
		urgencyInDb.setName(urgency.getName());
		
		return urgencyMapper.mapToUrgencyDTO(urgencyRepository.save(urgencyInDb));
	}
	
	public Boolean deleteUrgency(Integer id) {
		if(urgencyRepository.existsById(id)) {
			urgencyRepository.deleteById(id);
		} else {
			throw new UrgencyNotFoundException();
		}
		
		boolean doesItExistStill = urgencyRepository.existsById(id);
		
		return !doesItExistStill;
	}
}
