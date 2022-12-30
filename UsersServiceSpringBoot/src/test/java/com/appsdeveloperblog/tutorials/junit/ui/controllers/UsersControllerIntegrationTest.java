package com.appsdeveloperblog.tutorials.junit.ui.controllers;

import com.appsdeveloperblog.tutorials.junit.ui.response.UserRest;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


// Without any specific WebEnvironment the default is WebEnvironment.MOCK
// with this Spring App Context will only create
// the beans of the web layer and mock everything else!
// This means a mocked servlet environment!
// So we would need to use mockmvc in this case again!
// If you want use/start an embedded server on a specific/random
// port then you need a different webEnvironment.
//
// When working with a defined port you can
// use the port defined in the application.properties (server.port)
// or you can override the port via properties="server.port=8081".
// In case you want to override more than one property
// you have to use curly-brackets and separate the properties with a comma.
// Alternative you can use a different property file e. g. application-test.properties
// you can do this via the
// @TestPropertySource annotation. But it applies the following priority:
// application.properties < application-test.properties  < properties=...

//To avoid port number conflicts use WebEnvironment.RANDOM_PORT.
//The injected serverPort will be the property server.property
//defined in application.properties and this will be set to 0 (why?).
// To see the actually port
// you have to use the @LocalServerPort annotation!

/*@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
properties = {"server.port=8081", "hostname=192.168.0.2"})*/
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@TestPropertySource(locations = "/application-test.properties",
//properties = "server.port=8081")

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersControllerIntegrationTest {

    //inject property server.port
    @Value("${server.port}")
    private int serverPort;

    @LocalServerPort
    private int localServerPort;

    //Easier to use for user authentication in contrast to RestTemplate
    @Autowired
    private TestRestTemplate testRestTemplate;

//    @Test
//    void contextLoads() {
//        System.out.println("server.port=" + serverPort);
//        System.out.println("local server port =" + localServerPort);
//    }

    @Test
    @DisplayName("User can be created")
    void testCreateUser_whenValidDetailsIsProvided_returnsUserDetails() throws JSONException {
        //given
        JSONObject userDetailsRequestJson = new JSONObject();
        userDetailsRequestJson.put("firstName", "TestFirstName");
        userDetailsRequestJson.put("lastName", "TestLastName");
        userDetailsRequestJson.put("email", "test@gmail.com");
        userDetailsRequestJson.put("password", "12345678");
        userDetailsRequestJson.put("repeatPassword", "12345678");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(userDetailsRequestJson.toString(), headers);


        //when
        ResponseEntity<UserRest> createdUserDetailsEntity = testRestTemplate.postForEntity("/users", request,
                UserRest.class);

        UserRest createdUserDetails = createdUserDetailsEntity.getBody();

        //then
        assertEquals(HttpStatus.OK, createdUserDetailsEntity.getStatusCode());
        assertEquals(userDetailsRequestJson.getString("firstName"),
                createdUserDetails.getFirstName(),
                "Returned user's first name seems to be incorrect!"
        );
        assertEquals(userDetailsRequestJson.getString("lastName"),
                createdUserDetails.getLastName(),
                "Returned user's last name seems to be incorrect!"
        );
        assertEquals(userDetailsRequestJson.getString("email"),
                createdUserDetails.getEmail(),
                "Returned user's email seems to be incorrect!"
        );
        assertEquals(userDetailsRequestJson.getString("email"),
                createdUserDetails.getEmail(),
                "Returned user's email seems to be incorrect!"
        );
        assertFalse(createdUserDetails.getUserId().trim().isEmpty(),
                "User id should not be empty!");

    }

    @Test
    @DisplayName("GET /users requires JWT")
    void testGetUsers_whenMissingJWT_returns403() {
        //given
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        HttpEntity<Object> requestEntity = new HttpEntity<>(null, headers);


        //when
        //exchange used to send a get request and get back a list of objects
        //TODO: why exchange method???
        ResponseEntity<List<UserRest>> response = testRestTemplate.exchange("/users",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<UserRest>>() {
                });

        //then
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode(), "HTTP Status code 403" +
                "should have been returned!");
    }
}
