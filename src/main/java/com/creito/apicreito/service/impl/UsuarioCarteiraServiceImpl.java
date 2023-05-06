package com.creito.apicreito.service.impl;

import com.creito.apicreito.entity.UsuarioCarteira;
import com.creito.apicreito.repository.UsuarioCarteiraRepository;
import com.creito.apicreito.service.UsuarioCarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioCarteiraServiceImpl implements UsuarioCarteiraService {
    @Autowired
    UsuarioCarteiraRepository repository;
    @Override
    public UsuarioCarteira save(UsuarioCarteira uc) {
        return repository.save(uc);
    }

}
