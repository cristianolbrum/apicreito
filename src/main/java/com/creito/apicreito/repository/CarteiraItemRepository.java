package com.creito.apicreito.repository;

import com.creito.apicreito.dto.CarteiraItemDTO;
import com.creito.apicreito.entity.CarteiraItem;
import com.creito.apicreito.util.enums.TypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface CarteiraItemRepository extends JpaRepository<CarteiraItem, Long> {
    Page<CarteiraItem> findAllByCarteiraIdAndDateGreaterThanEqualAndDateLessThanEqual(Long carteira, Date init, Date end, Pageable page);
    List<CarteiraItem> findByCarteiraIdAndType(Long carteira, TypeEnum type);

    @Query(value = "select sum(value) from CarteiraItem wi where wi.carteira.id = :carteira")
    BigDecimal sumByCarteiraId(@Param("carteira") Long carteira);

}
