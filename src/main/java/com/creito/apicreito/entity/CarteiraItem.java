package com.creito.apicreito.entity;

import com.creito.apicreito.util.enums.TypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "carteira_itens")
@NoArgsConstructor
@AllArgsConstructor
public class CarteiraItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "carteira", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Carteira carteira;
    @NotNull
    private Date createdAt;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeEnum type;
    @NotNull
    private String description;
    @NotNull
    private BigDecimal amount;

}
