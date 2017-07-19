package com.garwan.assignment.todolist;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
	List<Task> findByCreatedById(Integer createdById);
	Task findByIdAndCreatedById(Integer id, Integer createdById);
}
