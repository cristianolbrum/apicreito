package com.creito.apicreito.controller;

import com.creito.apicreito.dto.UsuarioDTO;
import com.creito.apicreito.entity.Usuario;
import com.creito.apicreito.response.Response;
import com.creito.apicreito.service.UsuarioService;
import com.creito.apicreito.util.Bcrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<Response<UsuarioDTO>> create(@Valid @RequestBody UsuarioDTO dto, BindingResult result){
        Response<UsuarioDTO> response =  new Response<UsuarioDTO>();
        if(result.hasErrors()){
            result.getAllErrors().forEach(e-> response.getErrors().add(e.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Usuario usuario = service.save(this.convertDtoToEntity(dto));
        response.setData(this.convertEntityToDto(usuario));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private Usuario convertDtoToEntity(UsuarioDTO dto){
         Usuario u = new Usuario();
         u.setId(dto.getId());
         u.setName(dto.getName());
         u.setEmail(dto.getEmail());
         u.setPassword(Bcrypt.getHash(dto.getPassword()));
         return u;
    }

    private UsuarioDTO convertEntityToDto(Usuario u){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(u.getId());
        dto.setName(u.getName());
        dto.setEmail(u.getEmail());
        //dto.setPassword(u.getPassword());
        return dto;
    }
}
