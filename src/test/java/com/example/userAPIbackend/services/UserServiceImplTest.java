package com.example.userAPIbackend.services;

import com.example.userAPIbackend.entity.User;
import com.example.userAPIbackend.repository.UserRepository;
import com.example.userAPIbackend.security.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RetryService retryService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;




    @Test
    void getUser() {
        User validUser =
                User.builder().userId(1L).firstName("Agam").lastName("Aloni").email("aa@hfhg.com").
                        password("Qq!10000").build();

        when(userRepository.findByUserId(validUser.getUserId())).thenReturn(Optional.of(validUser));

        assertEquals(validUser,userService.getUser(validUser.getUserId()).get());
    }

}