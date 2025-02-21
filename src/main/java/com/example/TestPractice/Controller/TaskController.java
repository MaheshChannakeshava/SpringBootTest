package com.example.TestPractice.Controller;

import com.example.TestPractice.Model.Task;
import com.example.TestPractice.Service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks(){

        return taskService.getAllTask();
    }

    @PostMapping
    public Task createTask(@Valid Task task){
        return taskService.createTask(task);
    }

    @GetMapping("/{id}")
    public Task getByID(@PathVariable Long id){
        return taskService.getById(id);
    }


}
