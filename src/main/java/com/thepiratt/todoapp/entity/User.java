package com.thepiratt.todoapp.entity;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Todo> toDoList = new ArrayList<>();


    public User() {
    }

    public User(Long id, String username, String password, List<Todo> toDoList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.toDoList = toDoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Todo> getToDoList() {
        return toDoList;
    }

    public void setToDoList(List<Todo> toDoList) {
        this.toDoList = toDoList;
    }
}
