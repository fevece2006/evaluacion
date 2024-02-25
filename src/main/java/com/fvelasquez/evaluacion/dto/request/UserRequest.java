package com.fvelasquez.evaluacion.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequest {
    @NotEmpty
    private String name;
    @Email(
            message = "El email no tiene el formato correo (ejemplo: aaaaa@dominio.cl)",
            regexp = "(\\W|^)[\\w.\\-][\\w.\\-]{0,25}@dominio\\.cl"
    )
    private String email;
    @NotEmpty
    @Size(min = 8)
    private String password;
    private List<PhoneRequest> phones;
}
