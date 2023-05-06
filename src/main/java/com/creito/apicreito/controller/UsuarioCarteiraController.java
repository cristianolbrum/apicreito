package com.creito.apicreito.controller;

import com.creito.apicreito.dto.UsuarioCarteiraDTO;
import com.creito.apicreito.entity.Carteira;
import com.creito.apicreito.entity.Usuario;
import com.creito.apicreito.entity.UsuarioCarteira;
import com.creito.apicreito.response.Response;
import com.creito.apicreito.service.UsuarioCarteiraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Binding;

@RestController
@RequestMapping("usuario-carteira")
public class UsuarioCarteiraController {
    @Autowired
    private UsuarioCarteiraService service;

    @PostMapping
    public ResponseEntity<Response<UsuarioCarteiraDTO>> create(@Valid @RequestBody UsuarioCarteiraDTO dto, BindingResult result){
        Response<UsuarioCarteiraDTO> response = new Response<UsuarioCarteiraDTO>();
        if(result.hasErrors()){
            result.getAllErrors().forEach(r -> response.getErrors().add(r.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        UsuarioCarteira uc = this.service.save(convertDtoToEntity(dto));
        response.setData(this.convertEntityToDto(uc));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    private UsuarioCarteira convertDtoToEntity (UsuarioCarteiraDTO dto) {
        UsuarioCarteira uc = new UsuarioCarteira();
        Usuario u = new Usuario();
        u.setId(dto.getUsuarios());
        Carteira c = new Carteira();
        c.setId(dto.getCarteira());

        uc.setId(dto.getId());
        uc.setUsuarios(u);
        uc.setCarteira(c);

        return uc;
    }

    private UsuarioCarteiraDTO convertEntityToDto (UsuarioCarteira uc){
        UsuarioCarteiraDTO dto = new UsuarioCarteiraDTO();
        dto.setId(uc.getId());
        dto.setCarteira(uc.getCarteira().getId());
        dto.setUsuarios(uc.getUsuarios().getId());

        return dto;
    }
    }
