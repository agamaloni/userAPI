package com.example.userAPIbackend.services;

import com.example.userAPIbackend.entity.User;
import com.example.userAPIbackend.exception.SqlNotAvailableException;
import com.example.userAPIbackend.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getUser(Long id) throws UserNotFoundException, SqlNotAvailableException;
    User addUser(User user) throws SqlNotAvailableException;
    void deleteUser(Long id) throws UserNotFoundException, SqlNotAvailableException;
    List<User> getAllUsers() throws SqlNotAvailableException;

}
