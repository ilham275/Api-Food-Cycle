package com.main.coba2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.coba2.models.Toko;


public interface TokoRepository extends JpaRepository<Toko, Integer> {
    Toko findByNamaToko(String namaToko);
    Optional<Toko> findById(int idToko);
}
