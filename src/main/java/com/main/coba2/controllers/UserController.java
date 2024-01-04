package com.main.coba2.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.coba2.models.Toko;
import com.main.coba2.models.User;
import com.main.coba2.services.TokoService;
import com.main.coba2.services.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokoService tokoService;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        if (userService.authenticateUser(username, password)) {
            int id = userService.getUserByUsername(username).getId();
            String role = userService.getUserByUsername(username).getRole();
            user.setId(id);
            user.setRole(role);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(user);
        }
    }

    @PutMapping("/regist")
    public ResponseEntity<Toko> tokoregist(@RequestBody Toko toko) {
        User user = toko.getUser();
        System.out.println("IDUSER " + user.getId());
        Toko toko2 = tokoService.createToko(toko, user);
        return ResponseEntity.ok(toko2);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        Optional<User> userOptional = userService.getUserById(userId);

        if (userOptional.isPresent()) {
            userService.deleteUser(userId);
            return ResponseEntity.ok("User with ID " + userId + " successfully deleted");
        } else {
            return ResponseEntity.status(404).body("User with ID " + userId + " not found");
        }
    }

      @GetMapping("toko/all")
    public ResponseEntity<Iterable<Toko>> getAllToko() {
        Iterable<Toko> users = tokoService.getAllToko();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/detail/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        Optional<User> userOptional = userService.getUserById(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

     @DeleteMapping("/toko/{idToko}")
    public ResponseEntity<String> deleteToko(@PathVariable Integer idToko) {
        Optional<Toko> tokOptional = tokoService.getTokoById(idToko);
        if (tokOptional.isPresent()) {
            tokoService.deleteToko(idToko);
            return ResponseEntity.ok("Toko with ID " + idToko + " successfully deleted");
        } else {
            return ResponseEntity.status(404).body("Toko with ID " + idToko + " not found");
        }
    }

}
