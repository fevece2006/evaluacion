package com.fvelasquez.evaluacion.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import jakarta.validation.constraints.Pattern;

@Getter
@Setter
public class UserRequest {
    @NotEmpty
    private String name;
    @Email(
            message = "El email no tiene el formato correcto (ejemplo: xxxxx@dominio.cl)",
            regexp = "(\\W|^)[\\w.\\-][\\w.\\-]{0,25}@dominio\\.cl"
    )
    private String email;

    @NotEmpty
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "La contraseña debe contener al menos una letra y un número")
    private String password;

    private List<PhoneRequest> phones;
}
