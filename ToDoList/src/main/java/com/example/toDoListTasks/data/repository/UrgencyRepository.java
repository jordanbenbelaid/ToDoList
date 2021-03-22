package com.example.toDoListTasks.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.toDoListTasks.data.model.Urgency;

@Repository
public interface UrgencyRepository extends JpaRepository<Urgency, Integer>{

}
