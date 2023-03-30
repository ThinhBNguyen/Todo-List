package com.thinh.project.todo.dao;

import com.thinh.project.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUserName(String userName);
}
