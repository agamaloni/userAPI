package com.example.userAPIbackend.services;

import com.example.userAPIbackend.exception.SqlNotAvailableException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
@Slf4j
@Service
@EnableRetry
@AllArgsConstructor
public class RetryService {

    private DataSource dataSource;

    @Retryable(retryFor = SQLException.class, maxAttempts = 3, backoff = @Backoff(delay = 100))
    public void checkDBConnection() throws SqlNotAvailableException {
        try (Connection connection = dataSource.getConnection()) {
            log.warn("Database connection successful");
        } catch (SQLException e) {
            log.error("Database connection failed: " + e.getMessage());
            throw new SqlNotAvailableException(); // Throwing the exception to trigger a retry
        }
    }
}
