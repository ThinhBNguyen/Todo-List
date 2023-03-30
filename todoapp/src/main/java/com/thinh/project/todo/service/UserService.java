package com.thinh.project.todo.service;

import com.thinh.project.todo.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(int theId);

    User findByUserName(String userName);

    User save(User theUserEntity);

    void deleteById(int theId);
}
