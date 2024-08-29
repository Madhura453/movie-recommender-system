package com.rating.service;

import com.rating.model.User;
import com.rating.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;
    public User createUser(User user) {

        return userRepository.save(user);
    }
}
