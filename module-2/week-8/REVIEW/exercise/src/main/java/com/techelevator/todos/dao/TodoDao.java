package com.techelevator.todos.dao;

import com.techelevator.todos.model.Todo;
import java.util.List;

public interface TodoDao {
    // Create
    Todo createTodo(Todo todo);
    // Read
    List<Todo> getTodos();
    Todo getTodoById(int id);
    List<Todo> getTodosByTask(String searchTerm);

    // Update
    Todo updateTodo(Todo todo);

    // Delete
    int deleteTodoById(int todoId);
}
