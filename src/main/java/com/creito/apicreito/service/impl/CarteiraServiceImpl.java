package com.creito.apicreito.service.impl;

import com.creito.apicreito.entity.Carteira;
import com.creito.apicreito.repository.CarteiraRepository;
import com.creito.apicreito.service.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarteiraServiceImpl implements CarteiraService {

    @Autowired
    CarteiraRepository respository;
    @Override
    public Carteira save(Carteira c) {
        return respository.save(c);
    }
}
