package com.creito.apicreito.security.controller;

import com.creito.apicreito.response.Response;
import com.creito.apicreito.security.dto.JwtAuthenticationDTO;
import com.creito.apicreito.security.dto.TokenDTO;
import com.creito.apicreito.security.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<Response<TokenDTO>> gerarTokenJwt(
            @Valid @RequestBody JwtAuthenticationDTO authenticationDTO, BindingResult result) throws AuthenticationException{
        Response<TokenDTO> response = new Response<TokenDTO>();

        if(result.hasErrors()){
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationDTO.getEmail(), authenticationDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.getEmail());
        String token = jwtTokenUtil.getToken(userDetails);
        response.setData(new TokenDTO(token));

        return ResponseEntity.ok(response);
    }
}
