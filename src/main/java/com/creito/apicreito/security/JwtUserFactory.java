package com.creito.apicreito.security;

import com.creito.apicreito.entity.Usuario;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class JwtUserFactory {
    public static JwtUser create(Usuario user){
        return new JwtUser(user.getId(), user.getEmail(), user.getPassword());
    }
}
