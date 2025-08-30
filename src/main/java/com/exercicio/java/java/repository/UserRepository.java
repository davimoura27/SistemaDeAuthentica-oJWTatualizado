package com.exercicio.java.java.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.exercicio.java.java.entity.Users;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional <Users> findByEmail(String email);
    boolean existsByEmail(String email);
}
