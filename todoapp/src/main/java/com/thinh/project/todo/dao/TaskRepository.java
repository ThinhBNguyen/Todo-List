package com.thinh.project.todo.dao;

import com.thinh.project.todo.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Integer> {

    List<Task> findByUser_Id(int userId);
}
