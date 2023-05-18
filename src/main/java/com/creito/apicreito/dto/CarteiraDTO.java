package com.creito.apicreito.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CarteiraDTO {
    private Long id;
    @Length(min = 3, message = "O nome deve ter no minimo 3 caracteres")
    @NotNull
    private String name;
    @NotNull(message = "Insira um valor para a carteira")
    private BigDecimal value;
}
