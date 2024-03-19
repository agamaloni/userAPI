package com.example.userAPIbackend.repository;

import com.example.userAPIbackend.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}
