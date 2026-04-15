package com.example.demo.service;

import com.example.demo.entity.Usuarios;
import com.example.demo.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuariosServiceImplements implements UsuarioService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public Usuarios buscarPorUsername(String username) {
        // Simplemente llamamos al repo. Si no lo encuentra, el repo devolverá null.
        return usuariosRepository.findByUsername(username);
    }


    @Override
    public List<Usuarios> getAllUsuarios() { return usuariosRepository.findAll(); }

    @Override
    public Usuarios saveUsuario(Usuarios usuario) { return usuariosRepository.save(usuario); }

    @Override
    public void deleteUsuario(Integer id) { usuariosRepository.deleteById(id); }

    @Override
    public Usuarios getUsuarioById(Integer id) { return usuariosRepository.findById(id).orElse(null); }

    @Override
    public Usuarios updateUsuario(Integer id, Usuarios usuario) {
        usuario.setCodigo_usuario(id);
        return usuariosRepository.save(usuario);
    }
}