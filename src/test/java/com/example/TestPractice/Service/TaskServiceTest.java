package com.example.TestPractice.Service;

import com.example.TestPractice.Exception.TaskNotFoundException;
import com.example.TestPractice.Model.Task;
import com.example.TestPractice.Repo.TaskRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepo taskRepo;

    @Test
    void testFindAll(){
        //arrange
        List<Task> taskLists = Arrays.asList(
                new Task("Task 1", "To do"),
                new Task("Task 2"," Next Task")
        );

        when(taskRepo.findAll()).thenReturn(taskLists);

        //action
        List<Task> allTask = taskService.getAllTask();

        //assert
        assertEquals(2, allTask.size());
        assertNotNull(allTask);
        verify(taskRepo,times(1)).findAll();
    }

    @Test
    void createTask(){
        //arrange
        Task task = new Task("Task 4","New To do");
        when(taskRepo.save(any(Task.class))).thenReturn(task);

        //action

        Task createdTask = taskService.createTask(task);

        //assert
        assertNotNull(createdTask);
        assertEquals("Task 4", createdTask.getTaskTitle());
        verify(taskRepo, times(1)).save(task);
    }

    @Test
    void testFindById(){

        //arrange
        Task task = new Task(1L,"Task 1","To Do");
        when(taskRepo.findById(1L)).thenReturn(Optional.of(task));

        //action

        Task retrievedTask = taskService.getById(1L);

        //assert
        assertNotNull(retrievedTask);
        assertEquals(1L, retrievedTask.getId());
        verify(taskRepo,times(1)).findById(1L);

    }

    @Test
    void testFindById_NotFoundTask(){
        //arrange
        when(taskRepo.findById(1L)).thenReturn(Optional.empty());

        //assert and action
        assertThrows(TaskNotFoundException.class, ()-> taskService.getById(1L));
        verify(taskRepo, times(1)).findById(1L);


    }

    @Test
    void testUpdateStatus(){

        //arrange
        Task task = new Task(1L,"Task Update Status","Update");

        when(taskRepo.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepo.save(any(Task.class))).thenReturn(task);

        //action

        Task updatedTask = taskService.updateStatus(1L, "Updated Status");

        //assert
        assertNotNull(updatedTask);
        assertEquals("Updated Status", updatedTask.getTaskStatus());

        verify(taskRepo, times(1)).findById(1L);
        verify(taskRepo,times(1)).save(task);

    }
}
