package com.main.coba2.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.coba2.models.Toko;
import com.main.coba2.models.User;
import com.main.coba2.repository.TokoRepository;
import com.main.coba2.repository.UserRepository;

@Service
public class TokoService {
    @Autowired
    private TokoRepository tokoRepository;
    @Autowired
    private UserRepository userRepository;

    public Toko createToko(Toko toko, User user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + user.getId()));
        existingUser.setRole("toko");
        userRepository.save(existingUser);

        return tokoRepository.save(toko);
    }

     public Optional<Toko> getTokoById(int idToko) {
        return tokoRepository.findById(idToko);
    }

    public void deleteToko(Integer tokoId) {
        Toko toko = this.getTokoById(tokoId).get();
            User existingUser = userRepository.findById(toko.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Toko not found with id: " + toko.getUser().getId()));
        existingUser.setRole(null);
        userRepository.save(existingUser);
        tokoRepository.deleteById(tokoId);
    }

    public Iterable<Toko> getAllToko() {
        return tokoRepository.findAll();
    }
}
