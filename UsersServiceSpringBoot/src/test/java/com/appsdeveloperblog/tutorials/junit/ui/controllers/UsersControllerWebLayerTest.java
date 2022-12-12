package com.appsdeveloperblog.tutorials.junit.ui.controllers;

import com.appsdeveloperblog.tutorials.junit.ui.request.UserDetailsRequestModel;
import com.appsdeveloperblog.tutorials.junit.ui.response.UserRest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

// test only UsersController
// creates application context only related to Web Layer e. g. scan for controller beans, not data beans
@WebMvcTest(controllers = UsersController.class,
        excludeAutoConfiguration = {SecurityFilterAutoConfiguration.class}) // disable security filters
// a different approach to disable security filters
//@AutoConfigureMockMvc(addFilters = false)
public class UsersControllerWebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("User can be created")
    void testCreateUser_whenValidUserDetailsProvided_returnsCreatedUserDetails() throws Exception {
        // given
        UserDetailsRequestModel userDetailsRequestModel = new UserDetailsRequestModel();
        userDetailsRequestModel.setFirstName("testname");
        userDetailsRequestModel.setLastName("testlastname");
        userDetailsRequestModel.setEmail("test@mail.com");
        userDetailsRequestModel.setPassword("123456");
        userDetailsRequestModel.setRepeatPassword("123456");


        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userDetailsRequestModel));

        // when
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();
        UserRest userRest = new ObjectMapper().readValue(responseBodyAsString, UserRest.class);

        // then
        assertEquals(userDetailsRequestModel.getFirstName(),
                userRest.getFirstName(),
                "The returned users first name is incorrect"
        );
        assertEquals(userDetailsRequestModel.getLastName(),
                userRest.getLastName(),
                "The returned users last name is incorrect"
        );
        assertEquals(userDetailsRequestModel.getEmail(),
                userRest.getEmail(),
                "The returned users email is incorrect"
        );

        assertFalse(userRest.getUserId().isEmpty(), "userId should not be empty");

    }
}
