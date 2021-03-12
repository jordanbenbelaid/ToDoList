package com.example.toDoListTasks.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.toDoListTasks.data.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

}