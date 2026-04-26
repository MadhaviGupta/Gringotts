package com.dev.gringotts.service;

import com.dev.gringotts.entity.User;
import com.dev.gringotts.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
