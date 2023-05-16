package com.creito.apicreito.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTO {
    private Long id;
    @Length(min=3,max=50,message = "O nome deve cnonter entre 3 e 50 caracteres")
    private String name;
    @Email(message = "Email invalid")
    private String email;
    @NotNull
    @Length(min = 6, message = "A senha deve conter ao menos 6 caracteres")
    private String password;
}
