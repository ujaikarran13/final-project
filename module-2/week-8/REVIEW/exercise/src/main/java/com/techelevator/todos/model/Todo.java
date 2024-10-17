package com.techelevator.todos.model;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Todo {
    private int id;
    @NotBlank(message="Task must not be blank")
    private String task;
    private LocalDate dueDate;
    private boolean completed;
    private String createdBy;
    private List<String> collaborators;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCollaborators(List<String> collaborators) {
        if (collaborators == null) {
            this.collaborators = new ArrayList<String>();
        } else {
            this.collaborators = collaborators;
        }
    }

    public List<String> getCollaborators() {
        return this.collaborators;
    }

    public Todo(int id, String task, LocalDate dueDate, boolean completed, String createdBy, List<String> collaborators) {
        this.id = id;
        this.task = task;
        this.dueDate = dueDate;
        this.completed = completed;
        this.createdBy = createdBy;
        setCollaborators(collaborators);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", task='" + task + '\'' +
                '}';
    }
}
