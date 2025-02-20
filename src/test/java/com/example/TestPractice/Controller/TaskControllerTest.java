package com.example.TestPractice.Controller;

import com.example.TestPractice.Model.Task;
import com.example.TestPractice.Service.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @MockitoBean
    private TaskService taskService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateTask() throws Exception{
        Task task = new Task("Task for Controller","Controller check");
        when(taskService.createTask(any(Task.class))).thenReturn(task);

        //assert and act
        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskTitle").value("Task for Controller"));

    }

    @Test
    void testCreateTask_InvalidInput() throws Exception {
        Task task= new Task("","New task added");
        when(taskService.createTask(any(Task.class))).thenReturn(task);
        //assert and action

        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void testGetAllTasks() throws Exception {
        //arrange
        List<Task> taskLists = Arrays.asList(
                new Task("Task 1", "To do"),
                new Task("Task 2"," Next Task")
        );

        when(taskService.getAllTask()).thenReturn(taskLists);

        //assert and action

        mockMvc.perform(get("/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].taskTitle").value("Task 1"))
                .andExpect(jsonPath("$[1].taskTitle").value("Task 2"));

    }


}
