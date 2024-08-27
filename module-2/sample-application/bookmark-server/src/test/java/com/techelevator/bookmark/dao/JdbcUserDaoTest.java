package com.techelevator.bookmark.dao;

import com.techelevator.bookmark.model.Authority;
import com.techelevator.bookmark.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class JdbcUserDaoTest extends BaseDaoTests {

    private static final User USER_1 = new User(101, "user1", "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC","ROLE_USER", "User 1 - Jo Tagolia", "Test Image URL", "Test Bio");
    private static final User USER_2 = new User(102, "user2", "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC", "ROLE_USER", "User 2", null, null);
    private static final User USER_ADMIN = new User(100, "admin", "$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC", "ROLE_ADMIN", null, null, null);
    // Get should sort Users by username
    private static final List<User> ALL_USERS = Arrays.asList(new User[] {USER_ADMIN, USER_1, USER_2});

    private JdbcUserDao dao;

    @Before
    public void setup() {
        dao = new JdbcUserDao(dataSource);
    }

    @Test
    public void getUsers_returns_correct_list_sorted_by_name() {
        List<User> result = dao.getUsers();
        Assert.assertNotNull("getUsers returned null", result);
        assertUserListsMatch("getUsers returned wrong or partial data", ALL_USERS, result);
    }

    @Test
    public void getUserById_returns_correct_user() {
        User result = dao.getUserById(USER_1.getId());
        Assert.assertNotNull("getUserById returned null", result);
        assertUsersMatch("getUserById returned wrong or partial data", USER_1, result);
    }

    @Test
    public void getUserById_returns_null_when_id_not_found() {
        User result = dao.getUserById(9999);
        Assert.assertNull("getUserById failed to return null for id not in database", result);
    }

    @Test
    public void createUser_returns_user_with_id_and_correct_values() {
        User newUser = new User(0, "new_user", "password", "ROLE_TEST", null, null, null);
        User createdUser = dao.createUser(newUser.getUsername(), "password", "TEST");

        Assert.assertNotNull("createUser returned null", createdUser);

        int newId = createdUser.getId();
        Assert.assertTrue("createUser returned a user without an id", newId > 0);

        newUser.setId(newId);
        assertUsersMatch("createUser returned a user with wrong or partial data", newUser, createdUser);
    }

    @Test
    public void creatUser_has_expected_values_when_retrieved() {
        User newUser = new User(0, "new_user", "password", "ROLE_TEST", "New Test User", "Image Url", "Test Bio");
        User createdUser = dao.createUser(newUser.getUsername(), "password", "TEST");

        int newId = createdUser.getId();
        User retrievedUser = dao.getUserById(newId);

        assertUsersMatch("createUser did not save correct data in database", createdUser, retrievedUser);
    }

    @Test
    public void updateUser_returns_user_with_correct_values() {
        User user = new User();
        user.setId(USER_2.getId());
        user.setUsername(USER_2.getUsername());
        user.setAuthorities(USER_2.getAuthorities());

        // Change updatable fields
        user.setDisplayName("Modified Name");
        user.setProfileImageUrl("http://updated.com");
        user.setShortBio("Modified Bio");

        User result = dao.updateUser(user);
        assertUsersMatch("updateUser returned wrong or partial data", user, result);
    }

    @Test
    public void updateUser_has_expected_values_when_retrieved() {
        User user = new User();
        user.setId(USER_2.getId());
        user.setUsername(USER_2.getUsername());
        user.setAuthorities(USER_2.getAuthorities());

        // Change updatable fields
        user.setDisplayName("Modified Name");
        user.setProfileImageUrl("http://updated.com");
        user.setShortBio("Modified Bio");

        User result = dao.updateUser(user);
        User retrievedUser = dao.getUserById(result.getId());
        assertUsersMatch("updateUser did not save correct data in database", user, retrievedUser);
    }


    private void assertUsersMatch(String messagePrefix, User expected, User actual) {
        Assert.assertEquals(messagePrefix + ": User user_id field does not match expected value.", expected.getId(), actual.getId());
        Assert.assertEquals(messagePrefix + ": User (user_id=" + expected.getId() + ") contains unexpected data in field 'user_id'.",
                expected.getId(), actual.getId());
        Assert.assertEquals(messagePrefix + ": User (user_id=" + expected.getId() + ") contains unexpected data in field 'username'.",
                expected.getUsername(), actual.getUsername());
        // Password will be hashed differently each time, and is not returned by all queries, so ignore

        Assert.assertEquals(messagePrefix + ": User (user_id=" + expected.getId() + ") contains wrong number of authorities.",
                expected.getAuthorities().size(), actual.getAuthorities().size());
        for(Authority authority : expected.getAuthorities()){
            Assert.assertTrue(messagePrefix + ": User (user_id=" + expected.getId() + ") does not contain expected authority " + authority.getName(),
                    actual.getAuthorities().contains(authority));
        }

        Assert.assertEquals(messagePrefix + ": User display_name field does not match expected value.",
                expected.getDisplayName(), actual.getDisplayName());
        Assert.assertEquals(messagePrefix + ": User img_url field does not match expected value.",
                expected.getProfileImageUrl(), actual.getProfileImageUrl());
        Assert.assertEquals(messagePrefix + ": User short_bio field does not match expected value.",
                expected.getShortBio(), actual.getShortBio());
    }

    private void assertUserListsMatch(String messagePrefix, List<User> expectedList, List<User> actualList) {
        Assert.assertEquals(messagePrefix + ": List size incorrect.", expectedList.size(), actualList.size());
        for (int i=0; i< expectedList.size(); i++) {
            User expected = expectedList.get(i);
            User actual = actualList.get(i);
            if (i==0) {
                // For first item, if ids do not match, may indicate bad sorting
                Assert.assertEquals(messagePrefix + ": first user id does not match expected, check sort order",
                        expected.getId(), actual.getId());
            }
            assertUsersMatch(messagePrefix, expected, actual);
        }
    }
}
