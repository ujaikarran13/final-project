package com.techelevator.todos.dao;

import com.techelevator.todos.model.User;
import org.springframework.stereotype.Component;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class MemoryUserDao implements UserDao {

    private static List<User> users = new ArrayList<>();

    public MemoryUserDao() {
        if (users.isEmpty()) {
            setUsers();
        }
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                user = u;
            }
        }
        return user;
    }

    private void setUsers() {

        users.add(new User(1L, "admin", "$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi", "ROLE_ADMIN", true));
        users.add(new User(2L, "liam", "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC", "ROLE_USER", true));
        users.add(new User(3L, "jessa", "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC", "ROLE_USER", true));
        users.add(new User(4L, "antoni", "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC", "ROLE_USER", true));
        users.add(new User(5L, "sofia", "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC", "ROLE_USER", true));
        users.add(new User(6L, "mark", "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC", "ROLE_USER", true));
        users.add(new User(7L, "susan", "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC", "ROLE_USER", true));
        users.add(new User(8L, "jaden", "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC", "ROLE_USER", true));
    }

}
