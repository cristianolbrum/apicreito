package com.creito.apicreito.service.impl;

import com.creito.apicreito.entity.Carteira;
import com.creito.apicreito.entity.CarteiraItem;
import com.creito.apicreito.repository.CarteiraItemRepository;
import com.creito.apicreito.service.CarteiraItemService;
import com.creito.apicreito.service.CarteiraService;
import com.creito.apicreito.util.enums.TypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class CarteiraItemServiceImpl implements CarteiraItemService {

    @Autowired
    CarteiraItemRepository repository;

    @Value("${pagination.items_per_page}")
    protected int itemsPerPage;

    @Override
    public CarteiraItem save(CarteiraItem i) {
        return repository.save(i);
    }

    @Override
    public Page<CarteiraItem> findBetweenDates(Long carteira, Date start, Date end, int page) {
        @SuppressWarnings("deprecation")
        PageRequest pg = PageRequest.of(page, itemsPerPage);
        return repository.findAllByCarteiraIdAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(carteira,start,end,pg);
    }

    @Override
    public List<CarteiraItem> findByCarteiraAndType(Long carteira, TypeEnum type) {
        return repository.findByCarteiraIdAndType(carteira,type);
    }


    @Override
    public BigDecimal sumByCarteiraId(Long carteira) {
        return repository.sumByCarteiraId(carteira);
    }

    @Override
    public Optional<CarteiraItem> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deletById(Long id) {
        repository.deleteById(id);
    }


}
