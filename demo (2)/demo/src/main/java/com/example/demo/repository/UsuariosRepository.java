package com.example.demo.repository;

import com.example.demo.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    @Query("SELECT u FROM Usuarios u WHERE u.username = :username")
    Usuarios findByUsername(@Param("username") String username);
}