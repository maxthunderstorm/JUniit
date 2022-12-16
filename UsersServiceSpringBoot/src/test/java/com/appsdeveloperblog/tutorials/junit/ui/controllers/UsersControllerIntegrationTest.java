package com.appsdeveloperblog.tutorials.junit.ui.controllers;

import org.springframework.boot.test.context.SpringBootTest;

// Without any specific WebEnvironment the default is WebEnvironment.MOCK
// with this Spring App Context will only create
// the beans of the web layer and mock everything else!
// This means a mocked servlet enviornment!
// So we would need to use mockmvc in this case again!
// If you want use/start an embedded server on a specific/random
// port then you need a differen webEnvironment.
//
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UsersControllerIntegrationTest {
}
