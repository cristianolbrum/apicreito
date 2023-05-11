package com.creito.apicreito.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.aspectj.weaver.ast.Not;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CarteiraItemDTO {
    private Long id;
    @NotNull(message = "Insira o id da carteira")
    private Long carteira;
    @NotNull(message = "Informe uma data")
    private Date date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt-BR", timezone = "Brazil/East")
    @NotNull(message = "Informe um tipo")
    @Pattern(regexp = "(ENTRADA|SAIDA)$", message = "Para o tipo são aceitos apenas ENTRADA e SAIDA")
    private String type;
    @NotNull(message = "Informe uma descrição")
    @Length(min = 5, message = "A descrição deve ter no minimo 5 caracteres")
    private String description;
    @NotNull(message = "Informe um valor")
    private BigDecimal value;
}