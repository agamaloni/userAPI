package com.example.userAPIbackend.repository;

import com.example.userAPIbackend.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUserId(Long id);
    @Transactional
    void deleteByUserId(Long id);

}
