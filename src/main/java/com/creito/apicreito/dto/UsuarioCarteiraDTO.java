package com.creito.apicreito.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioCarteiraDTO {
    private Long id;
    @NotNull(message = "Informe o id do usuario")
    private Long usuarios;
    @NotNull(message = "Informe o id da carteira")
    private Long carteira;
}
