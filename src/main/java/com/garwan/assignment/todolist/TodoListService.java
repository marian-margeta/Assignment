package com.garwan.assignment.todolist;

import java.util.List;

public interface TodoListService {

	Task saveTask(Task task);
	void removeTask(int taskId);
	List<Task> getAllTasksForUser(int accoutId);
	Task getTask(int taskId, int accoutId);
	
}
