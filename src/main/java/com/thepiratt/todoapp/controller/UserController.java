package com.thepiratt.todoapp.controller;

import com.thepiratt.todoapp.entity.Todo;
import com.thepiratt.todoapp.entity.User;
import com.thepiratt.todoapp.repository.ToDoRespository;
import com.thepiratt.todoapp.repository.UserRespository;
import com.thepiratt.todoapp.request.AddTodoRequest;
import com.thepiratt.todoapp.request.AddUserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRespository userRepository;
    private ToDoRespository toDoRepository;

    public UserController(UserRespository userRespository, ToDoRespository toDoRespository) {
        this.userRepository = userRespository;
        this.toDoRepository = toDoRespository;
    }

    @GetMapping("/{Id}")
    public User getUserById(@PathVariable Long Id) {
        return userRepository.findById(Id).orElseThrow(() -> new NoSuchElementException());
    }

    @PostMapping
    public User addUser(@RequestBody AddUserRequest userRequest){
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());

        return userRepository.save(user);
    }

    @PostMapping("/{userId}/todos")
    public void addTodo(@PathVariable Long userId, @RequestBody AddTodoRequest todoRequest){
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        Todo todo = new Todo();
        todo.setContent(todoRequest.getContent());
        user.getToDoList().add(todo);
        userRepository.save(user);
    }

    @PostMapping("/todos/{todoId}")
    public void toggleTodoCompleted( @PathVariable Long todoId){
        Todo todo = toDoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
        todo.setCompleted(!todo.isCompleted());
        toDoRepository.save(todo);
    }


    @DeleteMapping("{userId}/todos/{todoId}")
    public void deleteTodo(@PathVariable Long userId,@PathVariable Long todoId){
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        Todo todo = toDoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
        user.getToDoList().remove(todo);
        toDoRepository.delete(todo);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        userRepository.delete(user);
    }
}
