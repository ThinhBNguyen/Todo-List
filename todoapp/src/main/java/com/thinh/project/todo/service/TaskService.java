package com.thinh.project.todo.service;

import com.thinh.project.todo.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();

    Task findById(int theId);

    Task save(Task theTask);

    void deleteById(int theId);

    List<Task> findByUser_Id(int userId);
}
