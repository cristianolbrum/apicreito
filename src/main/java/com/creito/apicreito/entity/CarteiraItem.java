package com.creito.apicreito.entity;

import com.creito.apicreito.util.enums.TypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "carteira-itens")
@NoArgsConstructor
@AllArgsConstructor
public class CarteiraItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "Carteira", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Carteira carteira;
    @NotNull
    private Date date;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeEnum type;
    @NotNull
    private String description;
    @NotNull
    private BigDecimal value;

}
