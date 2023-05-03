package com.creito.apicreito.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Data
public class CarteiraDTO {
    private Long id;
    @Length(min = 3)
    @NotNull
    private String name;
    @NotNull
    private BigDecimal value;
}
