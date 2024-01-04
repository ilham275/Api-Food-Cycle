package com.main.coba2.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.coba2.models.User;
import com.main.coba2.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Integer userId) {
        userRepository.deleteById(userId);
    }

    public boolean authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        System.out.println(password);
        if (user != null && password.equals(user.getPassword())) {
            return true; // Login berhasil
        }

        return false; // Login gagal
    }

    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

}
