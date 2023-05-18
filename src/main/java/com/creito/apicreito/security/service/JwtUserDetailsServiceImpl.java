package com.creito.apicreito.security.service;

import com.creito.apicreito.entity.Usuario;
import com.creito.apicreito.security.JwtUserFactory;
import com.creito.apicreito.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class JwtUserDetailsServiceImpl implements UserDetailsService {
@Autowired
UsuarioService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> user = userService.findByEmail(email);
        if (user.isPresent()){
            return JwtUserFactory.create(user.get());
        }
        throw new UsernameNotFoundException("Email não encontrado");
    }
}
