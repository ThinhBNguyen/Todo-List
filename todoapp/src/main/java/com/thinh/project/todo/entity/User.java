package com.thinh.project.todo.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="user")
public class User {
    //define fields and map to table "user"

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="username")
    private String userName;

    @Column(name="password")
    private String passWord;

    @Column(name="enabled")
    private int enabled;

    @Column(name="role")
    private String role;

    /*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user", referencedColumnName = "user_id")
    private List<Task> taskList;*/

    public User(int id, String userName, String passWord) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public User(){

    };
}
