package com.appsdeveloperblog.tutorials.junit.ui;

import com.appsdeveloperblog.tutorials.junit.io.UserEntity;
import com.appsdeveloperblog.tutorials.junit.io.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

// @DataJpaTest tests only JPA/Data-Layer
@DataJpaTest
public class UsersRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    void testFindByEmail_whenGivenCorrectEmail_returnUsersEntity() {
        //given
        UserEntity user = new UserEntity();
        user.setFirstName("TestFirst");
        user.setLastName("TestLast");
        user.setEmail("test@test.com");
        user.setUserId(UUID.randomUUID().toString());
        user.setEncryptedPassword("12345678");


        //persists entity in DB table
        testEntityManager.persistAndFlush(user);

        //when
        UserEntity storedUser = usersRepository.findByEmail(user.getEmail());

        //then

        assertEquals(user.getEmail(), storedUser.getEmail(), "The returned email adress does not" +
                "match the expected value");
    }
}
