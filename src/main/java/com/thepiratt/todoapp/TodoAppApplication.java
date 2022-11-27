package com.thepiratt.todoapp;

import com.thepiratt.todoapp.entity.Todo;
import com.thepiratt.todoapp.entity.User;
import com.thepiratt.todoapp.repository.ToDoRespository;
import com.thepiratt.todoapp.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoAppApplication implements CommandLineRunner {

	@Autowired
	private UserRespository userRespository;
	@Autowired
	private ToDoRespository toDoRespository;

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		User user = new User();
		user.setPassword("pasvord");
		user.setUsername("Harun");

		Todo todo = new Todo();
		todo.setContent("Zavrsi seminarski iz RS1");

		user.getToDoList().add(todo);

		userRespository.save(user);
	}
}
