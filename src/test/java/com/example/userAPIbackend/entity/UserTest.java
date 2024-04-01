package com.example.userAPIbackend.entity;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
class UserTest {

    User user = User.builder().build();

    @Test
    void setFirstNameNotNullSuccess() {
        user.setFirstName("Agam");
        assertEquals("Agam",user.getFirstName());
    }


    @Test
    void setLastNameSuccess() {
        user.setLastName("Aloni");
        assertEquals("Aloni",user.getLastName());
    }

}