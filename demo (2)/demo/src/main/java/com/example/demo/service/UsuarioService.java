package com.example.demo.service;

import com.example.demo.entity.Usuarios;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsuarioService {

    List<Usuarios> getAllUsuarios();

    Usuarios getUsuarioById(Integer id);

    Usuarios saveUsuario(Usuarios usuario) throws RuntimeException;

    Usuarios updateUsuario(Integer id, Usuarios usuario);

    void deleteUsuario(Integer id);

    Object buscarPorUsername(String username);
}