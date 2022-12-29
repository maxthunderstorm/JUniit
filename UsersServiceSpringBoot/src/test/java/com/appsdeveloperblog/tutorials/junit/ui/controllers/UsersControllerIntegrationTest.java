package com.appsdeveloperblog.tutorials.junit.ui.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

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

/*@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
properties = {"server.port=8081", "hostname=192.168.0.2"})*/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "/application-test.properties",
properties = "server.port=8081")
public class UsersControllerIntegrationTest {

    //inject property server.port
    @Value("${server.port}")
    private int serverPort;

    @Test
    void contextLoads() {
        System.out.println("server.port=" + serverPort);
    }
}
