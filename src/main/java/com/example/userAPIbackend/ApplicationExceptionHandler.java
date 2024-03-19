package com.example.userAPIbackend;

import com.example.userAPIbackend.exception.ErrorResponse;
import com.example.userAPIbackend.exception.SqlNotAvailableException;
import com.example.userAPIbackend.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(Collections.singletonList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SqlNotAvailableException.class)
    public ResponseEntity<Object> handleUserNotFoundException(SqlNotAvailableException ex) {
        ErrorResponse error = new ErrorResponse(Collections.singletonList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS);
    }

}
