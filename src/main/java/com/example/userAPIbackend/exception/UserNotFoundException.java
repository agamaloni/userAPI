package com.example.userAPIbackend.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long id) {
        super("The id '" + id + "' does not exist in our records");
    }
}
