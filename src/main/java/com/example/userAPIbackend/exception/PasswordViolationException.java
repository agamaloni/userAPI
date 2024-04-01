package com.example.userAPIbackend.exception;

public class PasswordViolationException extends RuntimeException {
    public PasswordViolationException(){
        super("The Password is not valid");
    }
}
