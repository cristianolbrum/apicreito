package com.creito.apicreito.service;

import com.creito.apicreito.entity.Carteira;

import java.util.Optional;

public interface CarteiraService {
    Carteira save(Carteira c);

    Optional<Carteira> find(Long c);
}
