package com.example.TestPractice.Exception;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String message){
        super(message);
    }
}
