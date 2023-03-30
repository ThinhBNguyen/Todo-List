package com.thinh.project.todo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name="task")
public class Task {

    public enum TaskStatus{
        NEW,
        IN_PROGRESS,
        COMPLETED
    }

    @Id
    @Column(name="task_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="due_date")
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name="user")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private TaskStatus status;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Task(){
    };

    public Task(String name, String description, LocalDate dueDate, TaskStatus status) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }
}
