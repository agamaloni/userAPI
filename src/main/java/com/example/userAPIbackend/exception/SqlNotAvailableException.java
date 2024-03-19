package com.example.userAPIbackend.exception;

public class SqlNotAvailableException extends RuntimeException {

    public SqlNotAvailableException(){
        super("The DB is unreachable");
    }
}
