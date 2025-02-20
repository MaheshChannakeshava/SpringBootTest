package com.example.TestPractice.Service;

import com.example.TestPractice.Exception.TaskNotFoundException;
import com.example.TestPractice.Model.Task;
import com.example.TestPractice.Repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepo taskRepo;

    public Task updateStatus(long id, String updatedStatus) {
            Task taskToUpdate = getById(id);
            taskToUpdate.setTaskStatus(updatedStatus);
            return taskRepo.save(taskToUpdate);

    }

    public Task getById(Long id){
        return taskRepo.findById(id).orElseThrow(()->new TaskNotFoundException("Task not found for the " +
                "id "+ id)  );
    }

    public List<Task> getAllTask() {
       return taskRepo.findAll();
    }

    public Task createTask(Task task) {
        return taskRepo.save(task);
    }
}
