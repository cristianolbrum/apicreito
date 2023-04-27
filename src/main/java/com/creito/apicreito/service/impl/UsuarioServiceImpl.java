package com.creito.apicreito.service.impl;

import com.creito.apicreito.entity.Usuario;
import com.creito.apicreito.repository.UsuarioRepository;
import com.creito.apicreito.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
   @Autowired
    UsuarioRepository repository;
    @Override
    public Usuario save(Usuario u) {
            return repository.save(u);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmailEquals(email);
    }
}
