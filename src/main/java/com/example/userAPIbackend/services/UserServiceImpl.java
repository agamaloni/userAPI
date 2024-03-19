package com.example.userAPIbackend.services;

import com.example.userAPIbackend.entity.User;
import com.example.userAPIbackend.exception.SqlNotAvailableException;
import com.example.userAPIbackend.exception.UserNotFoundException;
import com.example.userAPIbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    RetryService retryService;

    public UserServiceImpl(UserRepository userRepository, RetryService retryService) throws SqlNotAvailableException {
        this.userRepository = userRepository;
        this.retryService = retryService;
        isConnectionLive();
    }


    @Override
    public User getUser(Long id) throws UserNotFoundException, SqlNotAvailableException {
        isConnectionLive();
        return Optional.of(userRepository.findById(id)).get().orElseThrow(() ->new UserNotFoundException(id));
    }

    @Override
    public User addUser(User user) throws SqlNotAvailableException{
        isConnectionLive();
        return userRepository.save(user);
    }
    @Override
    public void deleteUser(Long id) throws UserNotFoundException, SqlNotAvailableException {
        isConnectionLive();
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
        else
            throw new UserNotFoundException(id);
    }
    @Override
    public List<User> getAllUsers() throws SqlNotAvailableException{
        isConnectionLive();
        return (List<User>) userRepository.findAll();
    }

    private void isConnectionLive() throws SqlNotAvailableException {
        retryService.checkDBConnection();

    }
}
