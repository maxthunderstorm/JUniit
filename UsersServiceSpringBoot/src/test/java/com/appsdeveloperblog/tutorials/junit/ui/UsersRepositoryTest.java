package com.appsdeveloperblog.tutorials.junit.ui;

import com.appsdeveloperblog.tutorials.junit.io.UserEntity;
import com.appsdeveloperblog.tutorials.junit.io.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// @DataJpaTest tests only JPA/Data-Layer
@DataJpaTest
public class UsersRepositoryTest {

    private static final String email = "test@test.com";
    private static final String uuid = UUID.randomUUID().toString();

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UsersRepository usersRepository;

    @BeforeEach
    void setUp() {
        UserEntity user = new UserEntity();
        user.setFirstName("TestFirst");
        user.setLastName("TestLast");
        user.setEmail(email);
        user.setUserId(UUID.randomUUID().toString());
        user.setEncryptedPassword("12345678");

        //persists entity in DB table
        testEntityManager.persistAndFlush(user);

        UserEntity user2 = new UserEntity();
        user2.setFirstName("Test2First");
        user2.setLastName("Test2Last");
        user2.setEmail("test2@test.com");
        user2.setUserId(uuid);
        user2.setEncryptedPassword("12345679");
        testEntityManager.persistAndFlush(user2);


    }

    @Test
    void testFindByEmail_whenGivenCorrectEmail_returnUsersEntity() {
        //given

        //when
        UserEntity storedUser = usersRepository.findByEmail(email);

        //then

        assertEquals(email, storedUser.getEmail(), "The returned email adress does not" +
                "match the expected value");
    }

    @Test
    void testFindByUserId_whenGivenCorrectUserId_returnUsersEntity() {
        //given

        //when
        UserEntity storedUser = usersRepository.findByUserId(uuid);

        //then
        assertNotNull(storedUser, "UserEntity should not be null");
        assertEquals(uuid, storedUser.getUserId(), "Returned userId does not match " +
                "expected value");
    }
}
