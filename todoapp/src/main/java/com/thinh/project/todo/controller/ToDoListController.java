package com.thinh.project.todo.controller;


import com.thinh.project.todo.entity.Task;
import com.thinh.project.todo.entity.User;
import com.thinh.project.todo.service.TaskService;
import com.thinh.project.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ToDoListController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "log-in";
    }

    /*@GetMapping("/tasks")
    public List<Task> findAll() {
        return taskService.findAll();
    }
*/
    /*@GetMapping("/tasks/{id}")
    public List<Task> getTaskById(@PathVariable int id){
        return taskService.findByUser_Id(id);
    }*/


    //endpoint to display list of tasks by current user
    @GetMapping("tasks")
    public String getUserTasks(Model theModel, Authentication authentication){

        //get current user from authentication
        String username = authentication.getName();
        User user = userService.findByUserName(username);
        int id = user.getId();
        //get user id to find tasks by user
        List<Task> theTasks = taskService.findByUser_Id(id);
        theModel.addAttribute("task",theTasks);
        return "tasklist";

    }

    //endpoint to display task form and bind data
    @GetMapping("/taskCreateForm")
    public String taskCreateForm(Model theModel, Authentication authentication){
        //create new empty task and bind to view attribute "task"
        Task theTask = new Task();
        theModel.addAttribute("task", theTask);
        return "task-form";
    }

    //endpoint to display task form and populate fields with task by id
    @GetMapping("/taskUpdateForm")
    public String taskUpdateForm(@RequestParam("taskId") int id,Model theModel){
        //populate task by taskId.
        Task theTask = new Task();
        theTask = taskService.findById(id);
        theModel.addAttribute("task",theTask);
        return "task-form";
    }

    //endpoint to display task form and bind data
    @GetMapping("/register")
    public String registerForm(Model theModel){
        User theUser = new User();
        theModel.addAttribute("user", theUser);
        return "register";
    }

    //endpoint to remove task by id
    @GetMapping("/tasks/delete")
    public String deleteTask(@RequestParam("taskId") int id, Model theModel){
        taskService.deleteById(id);
        return "redirect:/api/tasks";
    }


    //end point to send post request to add user to database
    @PostMapping("/register/save")
    public String createUser(@ModelAttribute("user") User theUser){
        theUser.setEnabled(1);
        theUser.setRole("USER");
        userService.save(theUser);
        return "redirect:/api/login?register";
    }


    //endpoint to save a new task by current user to database
    @PostMapping("/save")
    public String createTask(@ModelAttribute("task") Task theTask,Authentication authentication){

        //get current user from authentication
        String username = authentication.getName();
        User user = userService.findByUserName(username);
        theTask.setUser(user);
        //save the employee
        taskService.save(theTask);

        //Redirect to prevent duplicate submissions
        return "redirect:/api/tasks";
    }
}
