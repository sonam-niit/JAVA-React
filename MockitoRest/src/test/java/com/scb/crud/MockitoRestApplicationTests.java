package com.scb.crud;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scb.crud.entity.User;
import com.scb.crud.service.UserService;

@WebMvcTest
class MockitoRestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService service;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void userList() throws Exception {
		// given - precondition or setup
		List<User> list = new ArrayList<User>(Arrays.asList(new User(1, "alex", "alex@gmail.com"),
				new User(2, "bob", "bob@gmail.com"), new User(3, "catty", "catty@gmail.com")));
		when(service.findAll()).thenReturn(list);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(get("/api/users"));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.size()", is(list.size())));

	}

	@Test
	public void savedUser() throws Exception {

		User user = new User(1, "Sonam", "sonam@gmail.com");
		when(service.saveUser(Mockito.any(User.class))).thenAnswer(i -> i.getArguments()[0]);

		// when - action or behaviour that we are going test
		ResultActions response = mockMvc.perform(post("/api/users").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(user)));

		// then - verify the result or output using assert statements
		response.andDo(print()).andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(user.getId())))
				.andExpect(jsonPath("$.name", is(user.getName()))).andExpect(jsonPath("$.email", is(user.getEmail())));

	}

	// positive scenario - valid employee id
	// JUnit test for GET employee by id REST API
	@Test
	public void getUserById() throws Exception {
		// given - precondition or setup
		int userId = 1;
		User user = new User(1, "Sonam", "sonam@gmail.com");
		when(service.findById(userId)).thenReturn(Optional.of(user));

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(get("/api/users/{id}", userId));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.id", is(user.getId())))
				.andExpect(jsonPath("$.name", is(user.getName()))).andExpect(jsonPath("$.email", is(user.getEmail())));

	}

	// negative scenario - valid employee id
	// JUnit test for GET employee by id REST API
	@Test
	public void findByIdWithInvalidId() throws Exception {
		// given - precondition or setup
		int userId = 2;
		User user = new User(1, "Sonam", "sonam@gmail.com");
		when(service.findById(userId)).thenReturn(Optional.empty());

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(get("/api/users/{id}", userId));

		// then - verify the output
		response.andExpect(status().isNotFound()).andDo(print());

	}
	

	// JUnit test for update employee REST API - positive scenario
    @Test
    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturn200() throws Exception{
        // given - precondition or setup
        int userId = 1;
        User savedUser = new User(1, "sonam", "sonam@gmail.com");
		User updateUser = new User(1, "sonam soni", "sonam@gmail.com");
        given(service.findById(userId)).willReturn(Optional.of(savedUser));
        given(service.updateUser(Mockito.any(User.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/users/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateUser)));

     // then - verify the output
     		response.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$.id", is(updateUser.getId())))
     				.andExpect(jsonPath("$.name", is(updateUser.getName())))
     				.andExpect(jsonPath("$.email", is(updateUser.getEmail())));
    }

	
	// JUnit test for update employee REST API - Negative scenario
    @Test
    public void givenUpdatedEmployee_whenUpdateEmployee_thenReturn404() throws Exception{
        // given - precondition or setup
        int userId = 2;
        User savedUser = new User(1, "sonam", "sonam@gmail.com");
		User updateUser = new User(2, "sonam soni", "sonam@gmail.com");
        given(service.findById(userId)).willReturn(Optional.empty());
        given(service.updateUser(Mockito.any(User.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/users/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateUser)));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

	
	// JUnit test for delete employee REST API
	@Test
	public void givenEmployeeId_whenDeleteEmployee_thenReturn200() throws Exception {
		// given - precondition or setup
		int userID = 1;
		willDoNothing().given(service).deleteUser(userID);

		// when - action or the behaviour that we are going test
		ResultActions response = mockMvc.perform(delete("/api/users/{id}", userID));

		// then - verify the output
		response.andExpect(status().isOk()).andDo(print());

	}

}
