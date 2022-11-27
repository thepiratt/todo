package com.thepiratt.todoapp.repository;

import com.thepiratt.todoapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User,Long> {

}
