package com.uniplaystore.uniplay_backend.repository;

import com.uniplaystore.uniplay_backend.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends MongoRepository<User, String> {
    UserDetails findByLogin(String login);
    UserDetails findByEmail(String email);
}