package com.techelevator.todos.controller;

import com.techelevator.todos.dao.TodoDao;
import com.techelevator.todos.exception.DaoException;
import com.techelevator.todos.model.Todo;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class TodoController {
    private final TodoDao todoDao;

    public TodoController(TodoDao dao) {
        this.todoDao = dao;
    }

    @RequestMapping(path = "/todos", method = RequestMethod.GET)
    public List<Todo> list() {
        return todoDao.getTodos();
    }

    @RequestMapping(path = "/todos/{id}", method = RequestMethod.GET)
    public Todo get(@PathVariable int id) {
        Todo result = todoDao.getTodoById(id);
        if (result == null) {
            // 404 Not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        }
        return result;
    }

    @RequestMapping(path = "/todos/search", method = RequestMethod.GET)
    public List<Todo> getByTask(@RequestParam String task) {
        return todoDao.getTodosByTask(task);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/todos", method = RequestMethod.POST)
    public Todo add(@Valid @RequestBody Todo todo) {
        // TODO: Replace this line to store the current user's username.
        todo.setCreatedBy("unknown");
        Todo newTodo = todoDao.createTodo(todo);
        return newTodo;
    }

    @RequestMapping(path = "/todos/{id}", method = RequestMethod.PUT)
    public Todo update(@Valid @PathVariable int id, @RequestBody Todo todo) {
        // Confirm the id's match
        if (todo.getId() != id) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Todo id must match id in path");
        }
        try {
            Todo updatedTodo = todoDao.updateTodo(todo);
            return updatedTodo;
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error updating todo", e);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/todos/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        if (todoDao.deleteTodoById(id) == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not found");
        }
    }
}

