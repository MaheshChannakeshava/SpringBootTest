package com.example.TestPractice.Repo;


import com.example.TestPractice.Model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TaskRepoTest {

    @Autowired
    TaskRepo taskRepo;

    @Test
    void testSaveTask(){
        //arrange
        Task task = new Task("Test Save", "Saved");
//        task.setTaskTitle("Test status");
//        task.setTaskStatus("Checking");
        //action
        Task savedTask = taskRepo.save(task);
        //assert
        assertNotNull(savedTask);
        assertEquals("Test Save", savedTask.getTaskTitle());
    }

    @Test
    void testGetAll(){

        //Arrange
        Task task = new Task("Task1", "First Task");
        taskRepo.save(task);
        Task task1 = new Task("Task2", "Second Task");
        taskRepo.save(task1);
        //action
        List<Task> taskList = taskRepo.findAll();

        //assert
        assertEquals(2,taskList.size());

    }

    @Test
    void testFindById(){
        //arrange
        Task task = new Task("Task3", "Find Id Task");
        taskRepo.save(task);

        //action
        Optional<Task> getTask = taskRepo.findById(task.getId());

        //assert
        assertFalse(getTask.isEmpty());
        assertEquals(task.getId(), getTask.get().getId());

    }

    @Test
    void testUpdateTask(){
        //arrange
        Task task = new Task("Task3", "Find Id Task");
        taskRepo.save(task);

        //action
        task.setTaskStatus("Done");
        taskRepo.save(task);
        Optional<Task> getTaskStatus = taskRepo.findById(task.getId());

        //assert
        assertFalse(getTaskStatus.isEmpty());
        assertEquals("Done", getTaskStatus.get().getTaskStatus());


    }

    @Test
    void testDeleteTask(){

        //arrange
        Task task= new Task("Delete Task","deleted" );
//        task.setTaskTitle("");
//        task.setTaskStatus("");
        taskRepo.save(task);
        //arrange
        taskRepo.delete(task);
        Optional<Task> deleteTask= taskRepo.findById(task.getId());
        //action
        assertFalse(deleteTask.isPresent());
    }
}

