package com.example.demo.service;

import com.example.demo.entity.Usuarios;
import com.example.demo.repository.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosServiceImplements implements UsuarioService {

    private UsuariosRepository usuarioRepository;

    public UsuariosServiceImplements(UsuariosRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void UsuarioServiceimplements(UsuariosRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuarios> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuarios getUsuarioById(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public Usuarios saveUsuario(Usuarios usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuarios updateUsuario(Integer id, Usuarios usuario) {
        Usuarios existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no existe"));

        existente.setUsername(usuario.getUsername());
        existente.setPassword(usuario.getPassword());
        existente.setEmail(usuario.getEmail());
        existente.setRol(usuario.getRol());
        existente.setEstado(usuario.getEstado());

        return usuarioRepository.save(existente);
    }

    @Override
    public void deleteUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Este id no existe");
        }
        usuarioRepository.deleteById(id);
    }
}