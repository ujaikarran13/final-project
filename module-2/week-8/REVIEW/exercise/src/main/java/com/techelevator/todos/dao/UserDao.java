package com.techelevator.todos.dao;

import com.techelevator.todos.model.User;

public interface UserDao {

    User getUserByUsername(String username);

}
