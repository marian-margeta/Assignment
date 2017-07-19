package com.garwan.assignment;

import static java.util.Arrays.asList;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.garwan.assignment.auth.Account;
import com.garwan.assignment.auth.AccountRepository;
import com.garwan.assignment.todolist.Task;
import com.garwan.assignment.todolist.TaskRepository;

@SpringBootApplication
public class AssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssignmentApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(PasswordEncoder passwordEncoder, 
			AccountRepository accountRepository,
			TaskRepository taskRepository) {
	    return (args) -> {	    	
	        List<Account> accounts = asList(
	        		new Account("user1", passwordEncoder.encode("password1")),
	        		new Account("user2", passwordEncoder.encode("password2"))
    		);
	        accounts.stream().forEach(accountRepository::save);
	        

	        List<Task> tasks = asList(
	        		new Task("Sample task", accounts.get(0).getId(), false),
	        		new Task("Other task", accounts.get(0).getId(), false),
	        		new Task("Call mom", accounts.get(0).getId(), false),
	        		new Task("Feed dog", accounts.get(1).getId(), false),
	        		new Task("Write an email", accounts.get(1).getId(), false)
    		);
    		tasks.stream().forEach(taskRepository::save);	        
	    };
	}
}
