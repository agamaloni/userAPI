package com.example.userAPIbackend.services;

import com.example.userAPIbackend.entity.User;
import com.example.userAPIbackend.exception.PasswordViolationException;
import com.example.userAPIbackend.exception.SqlNotAvailableException;
import com.example.userAPIbackend.exception.UserNotFoundException;
import com.example.userAPIbackend.repository.UserRepository;
import com.example.userAPIbackend.security.PasswordEncoder;
import org.passay.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RetryService retryService;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, RetryService retryService) throws SqlNotAvailableException {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.retryService = retryService;
    }

    @Override
    public Optional<User> getUser(Long id) throws UserNotFoundException, SqlNotAvailableException {
        isConnectionLive();
        return Optional.of(userRepository.findByUserId(id))
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User addUser(User user) throws SqlNotAvailableException,PasswordViolationException {
        isConnectionLive();
        String password = user.getPassword();
        if(isPasswordValid(password)) {
            user.setPassword(passwordEncoder.encodePassword(password));
            return userRepository.save(user);
        } throw new PasswordViolationException();
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException, SqlNotAvailableException {
        isConnectionLive();
        if (userRepository.existsById(id)) {
            userRepository.deleteByUserId(id);
        } else throw new UserNotFoundException(id);
    }

    @Override
    public List<User> getAllUsers() throws SqlNotAvailableException {
        isConnectionLive();
        return (List<User>) userRepository.findAll();
    }

    private void isConnectionLive() throws SqlNotAvailableException {
        retryService.checkDBConnection();
    }

    public boolean isPasswordValid(String password) {
        PasswordValidator validator = new PasswordValidator(Arrays.asList(
                // at least 8 characters
                new LengthRule(8, 30),
                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                // at least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 1),
                // at least one symbol (special character)
                new CharacterRule(EnglishCharacterData.Special, 1),
                // no whitespace
                new WhitespaceRule()
        ));
        RuleResult result = validator.validate(new PasswordData(password));
        return result.isValid();
    }
}