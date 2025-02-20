package com.example.TestPractice.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import jakarta.validation.contraints.NotEmpty;
import lombok.NonNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message="Can't be empty")
    @Size(max= 100, message="Can't be mpre than 100 words")
    private String taskTitle;
    private String taskStatus;

    public Task(String taskTitle, String taskStatus) {
        this.taskTitle = taskTitle;
        this.taskStatus = taskStatus;
    }
}
