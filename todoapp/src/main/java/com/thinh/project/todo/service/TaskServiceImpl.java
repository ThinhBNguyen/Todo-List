package com.thinh.project.todo.service;


import com.thinh.project.todo.dao.TaskRepository;
import com.thinh.project.todo.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(int theId) {
        Optional<Task> result = taskRepository.findById(theId);

        Task theTask = null;

        if (result.isPresent()) {
            theTask = result.get();
        }
        else {
            throw new RuntimeException("Did not find task id - " + theId);
        }

        return theTask;
    }

    @Override
    public Task save(Task theTask) {
        return taskRepository.save(theTask);
    }

    @Override
    public void deleteById(int theId) {
        taskRepository.deleteById(theId);
    }

    @Override
    public List<Task> findByUser_Id(int userId) {
        return taskRepository.findByUser_Id(userId);
    }
}
