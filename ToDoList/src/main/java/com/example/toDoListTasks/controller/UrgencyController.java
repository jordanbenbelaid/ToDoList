package com.example.toDoListTasks.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.toDoListTasks.data.model.Urgency;
import com.example.toDoListTasks.dto.UrgencyDTO;
import com.example.toDoListTasks.service.UrgencyService;

@RestController
@RequestMapping("/urgency")
@CrossOrigin
public class UrgencyController {

	private UrgencyService urgencyService;

	@Autowired
	public UrgencyController(UrgencyService urgencyService) {
		this.urgencyService = urgencyService;
	}

	@GetMapping
	public ResponseEntity<List<UrgencyDTO>> getAllUrgencies() {
		List<UrgencyDTO> data = urgencyService.readAllUrgency();

		return new ResponseEntity<List<UrgencyDTO>>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UrgencyDTO> createUrgency(@Valid @RequestBody Urgency urgency) {
		UrgencyDTO newUrgency = urgencyService.createUrgency(urgency);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(newUrgency.getName()));

		return new ResponseEntity<UrgencyDTO>(newUrgency, headers, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UrgencyDTO> updateUrgency(@PathVariable("id") Integer id, @RequestBody Urgency urgency) {
		UrgencyDTO updatedUrgency = urgencyService.updateUrgency(id, urgency);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", String.valueOf(updatedUrgency.getId()));

		return new ResponseEntity<UrgencyDTO>(updatedUrgency, headers, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteUrgency(@PathVariable("id") int id) {		
		return new ResponseEntity<Boolean>(urgencyService.deleteUrgency(id), HttpStatus.OK);
	}
}
