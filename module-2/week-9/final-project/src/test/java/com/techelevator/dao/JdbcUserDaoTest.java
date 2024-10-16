package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcUserDaoTest extends BaseDaoTest {
    protected static final User USER_1 = new User(1, "user1", "user1", "ROLE_USER");
    protected static final User USER_2 = new User(2, "user2", "user2", "ROLE_USER");
    private static final User USER_3 = new User(3, "user3", "user3", "ROLE_USER");

    private JdbcUserDao dao;

    @BeforeEach
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcUserDao(jdbcTemplate);
    }

    @Test
    public void getUserByUsername_given_null_returns_null_user() {
        User user = dao.getUserByUsername(null);
        assertNull(user, "getUserByUsername with null username did not return null user");
    }

    @Test
    public void getUserByUsername_given_invalid_username_returns_null_user() {
        User user = dao.getUserByUsername("invalid");
        assertNull(user, "getUserByUsername with invalid username did not return null user");
    }

    @Test
    public void getUserByUsername_given_valid_user_returns_correct_user() {
        User actualUser = dao.getUserByUsername(USER_1.getUsername());
        assertEquals(USER_1, actualUser,
                "getUserByUsername with valid username did not return correct user");
    }

    @Test
    public void getUserById_given_invalid_user_id_returns_null() {
        User user = dao.getUserById(-1);
        assertNull(user, "getUserById with invalid userId did not return null user");
    }

    @Test
    public void getUserById_given_valid_user_id_returns_user() {
        User actualUser = dao.getUserById(USER_1.getId());
        assertEquals(USER_1, actualUser, "getUserById with valid id did not return correct user");
    }

    @Test
    public void findAll_returns_all_users() {
        List<User> users = dao.getUsers();

        assertNotNull(users, "getUsers returned a null list of users");
        assertEquals(3, users.size(), "getUsers returned a list with the incorrect number of users");
        assertEquals(USER_1, users.get(0), "getUsers returned a list in incorrect order");
        assertEquals(USER_2, users.get(1), "getUsers returned a list in incorrect order");
        assertEquals(USER_3, users.get(2), "getUsers returned a list in incorrect order");
    }

    @Test
    public void create_user_with_null_username() {
        try {
            dao.createUser(new User(null, USER_3.getHashedPassword(), "ROLE_USER"));
            fail("Expected createUser() with null username to throw DaoException, but it didn't throw any exception");
        } catch (DaoException e) {
            // expected condition
        } catch (Exception e) {
            fail("Expected createUser() with null username to throw DaoException, but threw a different exception");
        }
    }

    @Test
    public void create_user_with_existing_username() {
        try {
            dao.createUser(new User(USER_1.getUsername(), USER_3.getHashedPassword(), "ROLE_USER"));
            fail("Expected createUser() with existing username to throw DaoException, but it didn't throw any exception");
        } catch (DaoException e) {
            // expected condition
        } catch (Exception e) {
            fail("Expected createUser() with existing username to throw DaoException, but threw a different exception");
        }
    }

    @Test
    public void create_user_with_null_password() {
        try {
            dao.createUser(new User(USER_3.getUsername(), null, "ROLE_USER"));
            fail("Expected createUser() with null password to throw DaoException, but it didn't throw any exception");
        } catch (DaoException e) {
            // expected condition
        } catch (Exception e) {
            fail("Expected createUser() with null password to throw DaoException, but threw a different exception");
        }
    }

    @Test
    public void create_user_creates_a_user() {
        User newUser = new User("new", "user", "ROLE_USER");

        User user = dao.createUser(newUser);
        assertNotNull(user, "Call to create should return non-null user");

        User actualUser = dao.getUserById(user.getId());
        assertNotNull(actualUser, "Call to getUserById after call to create should return non-null user");

        newUser.setId(actualUser.getId());
        actualUser.setHashedPassword(newUser.getHashedPassword()); // reset password back to unhashed password for testing
        assertEquals(newUser, actualUser);
    }
}
