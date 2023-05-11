package com.creito.apicreito.service;

import com.creito.apicreito.entity.Carteira;
import com.creito.apicreito.entity.CarteiraItem;
import com.creito.apicreito.util.enums.TypeEnum;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CarteiraItemService {
    CarteiraItem save(CarteiraItem i);

    Page<CarteiraItem> findBetweenDates(Long carteira, Date start, Date end, int page);

    List<CarteiraItem> findByCarteiraAndType(Long carteira, TypeEnum type);

    BigDecimal sumByCarteiraId(Long carteira);

    Optional<CarteiraItem> findById(Long id);

    void deletById(Long id);
}
