package com.creito.apicreito.service;

import com.creito.apicreito.entity.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Usuario save(Usuario u);
    Optional<Usuario> findByEmail(String email);
}
