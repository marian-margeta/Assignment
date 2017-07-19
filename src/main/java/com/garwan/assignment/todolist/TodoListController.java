package com.garwan.assignment.todolist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.garwan.assignment.auth.UserAccount;

@Controller
public class TodoListController {
	@Autowired private TodoListService todoListService;
	
	@ModelAttribute("username")
	public String getVersion(@AuthenticationPrincipal UserAccount account) {
	   return account.getUsername();
	}
	
	@RequestMapping("/")
	public String allTasks(ModelMap model, @AuthenticationPrincipal UserAccount account) {
		
		List<Task> tasks = todoListService.getAllTasksForUser(account.getId());
		model.put("tasks", tasks);
		
		return "todo-list";
	}
}
