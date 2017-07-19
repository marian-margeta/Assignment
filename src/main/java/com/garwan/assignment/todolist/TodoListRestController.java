package com.garwan.assignment.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.garwan.assignment.auth.UserAccount;

@RestController
@RequestMapping("/todo-list")
public class TodoListRestController {

	@Autowired private TodoListService todoListService;
	
	@RequestMapping(value = "/{taskId}", method = RequestMethod.DELETE)
	public void deleteTask(@PathVariable Integer taskId) throws Exception {
		todoListService.removeTask(taskId);
    }

	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Task addTask(@RequestBody TaskDto taskDto, @AuthenticationPrincipal UserAccount account) 
			throws Exception {
		Task task = new Task(taskDto.getText(), account.getId(), false);
		
		return todoListService.saveTask(task);
    }
	
	@ResponseBody
	@RequestMapping(value = "/{taskId}", method = RequestMethod.PUT)
	public Task addTask(@PathVariable Integer taskId, @RequestBody TaskDto taskDto, @AuthenticationPrincipal UserAccount account) 
			throws Exception {
		Task task = todoListService.getTask(taskId, account.getId());
		task.setTitle(taskDto.getText());
			
		return todoListService.saveTask(task);
    }
}
