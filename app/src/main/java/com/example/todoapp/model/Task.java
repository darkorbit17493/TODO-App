package com.example.todoapp.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    private String name;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private boolean isCompleted;

    public Task() {
    }

    public Task(String name, String description, LocalDate date, LocalTime time) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.time = time;
        this.isCompleted = false;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.isCompleted = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
