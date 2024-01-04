package com.main.coba2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.coba2.models.User;
import java.util.Optional;



public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    Optional<User> findById(int id);
}
