package com.garwan.assignment.todolist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TodoListServiceImpl implements TodoListService {
	@Autowired private TaskRepository taskRepository;

	public Task saveTask(Task task) {
		return taskRepository.save(task);
	}
	
	public void removeTask(int taskId) {
		taskRepository.delete(taskId);
	}
	
	public Task getTask(int taskId, int accoutId) {
		return taskRepository.findByIdAndCreatedById(taskId, accoutId);
	}

	public List<Task> getAllTasksForUser(int accoutId) {
		return taskRepository.findByCreatedById(accoutId);
	}
	
	
}
