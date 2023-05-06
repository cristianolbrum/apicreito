package com.creito.apicreito.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "usuarios_carteira")
@Data
public class UsuarioCarteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "usuarios", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuarios;
    @JoinColumn(name = "carteira", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Carteira carteira;

}
