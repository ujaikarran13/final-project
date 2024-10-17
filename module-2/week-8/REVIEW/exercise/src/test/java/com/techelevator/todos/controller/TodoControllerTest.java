package com.techelevator.todos.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techelevator.todos.model.Todo;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TodoControllerTest {

    private static final int INITIAL_ID = -1;

    @Autowired
    TodoController todoController;

    @Autowired
    MockMvc mockMvc;
    Todo todo;
    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setup() {
        SecurityContextHolder.clearContext();
        todo = new Todo(INITIAL_ID, "test", LocalDate.now(), false, "test", Arrays.asList("mark"));
    }

    @Test
    public void STEP1_unauthenticated_requester_cant_access_any_endpoint() throws Exception {

        MvcResult result = mockMvc.perform(get("/todos")).andReturn();
        assertEquals("Unauthenticated user can access endpoint GET /todos",
                HttpStatus.UNAUTHORIZED.value(), result.getResponse().getStatus());

        result = mockMvc.perform(get("/todos/1")).andReturn();
        assertEquals("Unauthenticated user can access endpoint GET /todos/{id}",
                HttpStatus.UNAUTHORIZED.value(), result.getResponse().getStatus());

        result = mockMvc.perform(get("/todos/search?task=test")).andReturn();
        assertEquals("Unauthenticated user can access endpoint GET /todos/search",
                HttpStatus.UNAUTHORIZED.value(), result.getResponse().getStatus());

        result = mockMvc.perform(post("/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(todo))).
                andReturn();
        assertEquals("Unauthenticated user can access endpoint POST /todos",
                HttpStatus.UNAUTHORIZED.value(), result.getResponse().getStatus());

        result = mockMvc.perform(put("/todos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(todo)))
                .andReturn();
        assertEquals("Unauthenticated user can access endpoint PUT /todos/{id}",
                HttpStatus.UNAUTHORIZED.value(), result.getResponse().getStatus());

        result = mockMvc.perform(delete("/todos/1")).andReturn();
        assertEquals("Unauthenticated user can access endpoint DELETE /todos/{id}",
                HttpStatus.UNAUTHORIZED.value(), result.getResponse().getStatus());
    }

    @Test
    @WithMockUser("susan")
    public void STEP2_user_can_create_new_todo() throws Exception {
        MvcResult result = mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(todo))).andReturn();
        assertEquals("Didn't receive 201 CREATED when creating new Todo as user susan",
                HttpStatus.CREATED.value(), result.getResponse().getStatus());

        Todo returnedTodo = todoFromJson(result.getResponse().getContentAsString());
        assertNotEquals("ID not updated when new Todo created", INITIAL_ID, returnedTodo.getId());

        MvcResult retrieveResult = mockMvc.perform(get("/todos/" + returnedTodo.getId())).andReturn();
        Todo retrievedTodo = todoFromJson(retrieveResult.getResponse().getContentAsString());
        assertEquals("createdBy not set to user that created the Todo", "susan", retrievedTodo.getCreatedBy());
    }

    @Test
    @WithMockUser("jessa")
    public void STEP3_1_user_can_retrieve_single_todo_they_created() throws Exception {
        MvcResult result = mockMvc.perform(get("/todos/6")).andReturn();

        assertEquals("Didn't receive 200 OK when retrieving Todo that user jessa created",
                HttpStatus.OK.value(), result.getResponse().getStatus());

        Todo todo = todoFromJson(result.getResponse().getContentAsString());
        assertEquals("GET /todos/6 didn't return correct Todo for user jessa", 6, todo.getId());
    }

    @Test
    @WithMockUser("jessa")
    public void STEP3_2_user_can_retrieve_single_todo_they_collaborate_on() throws Exception {
        MvcResult result = mockMvc.perform(get("/todos/7")).andReturn();

        assertEquals("Didn't receive 200 OK when retrieving Todo user jessa collaborates on",
                HttpStatus.OK.value(), result.getResponse().getStatus());

        Todo todo = todoFromJson(result.getResponse().getContentAsString());
        assertEquals("GET /todos/7 didn't return correct Todo for user jessa", 7, todo.getId());
    }

    @Test
    @WithMockUser("liam")
    public void STEP3_3_user_cant_retrieve_todo_they_didnt_create_or_collaborate_on() throws Exception {
        MvcResult result = mockMvc.perform(get("/todos/7")).andReturn();

        assertEquals("Didn't receive 403 FORBIDDEN when retrieving Todo that user liam didn't" +
                        " create or collaborate on",
                HttpStatus.FORBIDDEN.value(), result.getResponse().getStatus());
    }

    @Test
    @WithMockUser("jessa")
    public void STEP3_4_user_can_retrieve_todos() throws Exception {
        MvcResult result = mockMvc.perform(get("/todos")).andReturn();

        assertEquals("Didn't receive 200 OK when retrieving todos as user jessa",
                HttpStatus.OK.value(), result.getResponse().getStatus());

        Todo[] todos = todoArrayFromJson(result.getResponse().getContentAsString());
        assertEquals("GET /todos didn't return correct number of Todos for user jessa", 4, todos.length);
    }

    @Test
    @WithMockUser("jaden")
    public void STEP3_5_user_with_no_todos_gets_empty_list() throws Exception {
        MvcResult result = mockMvc.perform(get("/todos")).andReturn();

        assertEquals("Didn't receive 200 OK when retrieving todos as user jaden",
                HttpStatus.OK.value(), result.getResponse().getStatus());

        Todo[] todos = todoArrayFromJson(result.getResponse().getContentAsString());
        assertNotNull("GET /todos should return an empty list, not null, for user jaden", todos);
        assertEquals("GET /todos didn't return an empty list for user jaden", 0, todos.length);
    }

    @Test
    @WithMockUser("liam")
    public void STEP3_6_user_can_search_todos() throws Exception {
        MvcResult result = mockMvc.perform(get("/todos/search")
                        .queryParam("task", "research")).
                andReturn();

        assertEquals("Didn't receive 200 OK when searching todos for \"research\" as user liam",
                HttpStatus.OK.value(), result.getResponse().getStatus());

        Todo[] todos = todoArrayFromJson(result.getResponse().getContentAsString());
        assertEquals("GET /todos/search?task=research as user liam didn't return correct number of Todos." +
                " Search is case-insensitive and should only return matching Todos that liam owns or is a contributor on", 1, todos.length);
    }

    @Test
    @WithMockUser("jessa")
    public void STEP4_1_user_createdby_can_update_todo() throws Exception {
        Todo updatedTodo = new Todo(4, "Updated task", LocalDate.now(), false, null, null);
        MvcResult result = mockMvc.perform(put("/todos/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(updatedTodo))).andReturn();
        assertEquals("Didn't receive 200 OK when updating existing Todo as user jessa",
                HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    @WithMockUser("antoni")
    public void STEP4_2_user_collaborator_can_update_todo() throws Exception {
        Todo updatedTodo = new Todo(4, "Updated task", LocalDate.now(), false, null, null);
        MvcResult result = mockMvc.perform(put("/todos/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(updatedTodo))).andReturn();
        assertEquals("Didn't receive 200 OK when updating existing Todo as user antoni",
                HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    @WithMockUser("jessa")
    public void STEP4_3_user_cant_update_todo_they_didnt_create_or_collaborate_on() throws Exception {
        Todo updatedTodo = new Todo(1, "Updated task", LocalDate.now(), false, null, null);
        MvcResult result = mockMvc.perform(put("/todos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(updatedTodo))).andReturn();
        assertEquals("Didn't receive 403 FORBIDDEN when updating Todo that user jessa didn't" +
                        " create or collaborate on",
                HttpStatus.FORBIDDEN.value(), result.getResponse().getStatus());
    }

    @Test
    @WithMockUser(value = "admin", roles = {"ADMIN"})
    public void STEP5_1_admin_can_delete_todo() throws Exception {
        MvcResult result = mockMvc.perform(delete("/todos/1")).andReturn();
        assertEquals("Didn't receive 204 NO CONTENT when deleting Todo as an ADMIN user",
                HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
    }

    @Test
    @WithMockUser("antoni")
    public void STEP5_2_nonadmin_cant_delete_todo() throws Exception {
        MvcResult result = mockMvc.perform(delete("/todos/1")).andReturn();
        assertEquals("Didn't receive 403 FORBIDDEN when deleting Todo as a non-ADMIN user",
                HttpStatus.FORBIDDEN.value(), result.getResponse().getStatus());
    }

    // -----------------------------------------------------------------------------
    // Private helper methods
    // -----------------------------------------------------------------------------

    private String toJson(Object obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    private Todo[] todoArrayFromJson(String json) throws JsonProcessingException {
        return mapper.readValue(json, Todo[].class);
    }

    private Todo todoFromJson(String json) throws JsonProcessingException {
        return mapper.readValue(json, Todo.class);
    }
}
