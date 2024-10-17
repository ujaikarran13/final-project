package com.techelevator.todos.dao;

import com.techelevator.todos.exception.DaoException;
import com.techelevator.todos.model.Todo;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MemoryTodoDao implements TodoDao {

    List<Todo> todos;

    public MemoryTodoDao() {
        LocalDate now = LocalDate.now();
        todos = new ArrayList<>();

        todos.add( new Todo(1, "Decide on vacation location", now.plusWeeks(52).minusDays(1), true, "mark", Arrays.asList("sofia", "liam")));
        todos.add( new Todo(2, "Research hotels", now.plusWeeks(53).minusDays(1), false, "sofia", Arrays.asList( "mark", "liam" )));
        todos.add( new Todo(3, "Make reservations", now.plusWeeks(54).plusDays(3), false, "liam", null));

        // Credit to https://www.homelight.com/blog/buyer-steps-to-building-a-house/
        todos.add( new Todo(4, "Find and purchase the lot", now.minusWeeks(25), true, "jessa", Arrays.asList( "antoni")));
        todos.add( new Todo(5, "Research the type of house", now.minusWeeks(18), true, "jessa", Arrays.asList( "antoni")));
        todos.add( new Todo(6, "Research and hire the building team", now.minusWeeks(12), true, "jessa", Arrays.asList( "antoni")));
        todos.add( new Todo(7, "Get the required permits from the township", now.minusWeeks(8), true, "antoni", Arrays.asList( "jessa" )));
    }

    @Override
    public List<Todo> getTodos() {
        List<Todo> matchingTodos = new ArrayList<>(todos);
        return matchingTodos;
    }

    @Override
    public Todo getTodoById(int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        throw new DaoException("Todo not found");
    }

    @Override
    public List<Todo> getTodosByTask(String searchTerm) {
        List<Todo> results = new ArrayList<>();
        for (Todo todo : todos) {
            if (todo.getTask().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(todo);
            }
        }
        return results;
    }

    @Override
    public Todo createTodo(Todo todo) {
        if (todo == null) {
            throw new DaoException("Todo is null");
        }
        Todo newTodo = new Todo(nextId(), todo.getTask(), todo.getDueDate(), todo.getCompleted(), todo.getCreatedBy(), todo.getCollaborators());
        todos.add(newTodo);
        return newTodo;
    }

    @Override
    public int deleteTodoById(int todoId) {
        Todo todo = getTodoById(todoId);
        if (todo != null) {
            todos.remove(todo);
            return 1; // number of rows affected
        } else {
            return 0; // number of rows affected
        }
    }

    @Override
    public Todo updateTodo(Todo updatedTodo) {
        Todo todo = getTodoById(updatedTodo.getId());
        if (todo == null) {
            throw new DaoException("Todo to update not found");
        }
        // Can't update id or createdBy
        todo.setCompleted(updatedTodo.getCompleted());
        todo.setDueDate(updatedTodo.getDueDate());
        todo.setTask(updatedTodo.getTask());
        todo.setCollaborators(updatedTodo.getCollaborators());
        return todo;
    }

    private int nextId() {
        // Find the current max
        int maxId = 0; // IDs are always positive
        for (Todo todo : todos) {
            if (todo.getId() > maxId) {
                maxId = todo.getId();
            }
        }
        return maxId + 1;
    }
}
