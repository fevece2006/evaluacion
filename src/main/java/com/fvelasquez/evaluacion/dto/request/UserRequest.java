package com.fvelasquez.evaluacion.dto.request;

import com.fvelasquez.evaluacion.utils.Constantes;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequest {
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String name;

    @Email(
            message = "El email no tiene el formato correo (ejemplo: aaaaa@dominio.cl)",
            regexp = "(\\W|^)[\\w.\\-][\\w.\\-]{0,25}@dominio\\.cl"
    )
    private String email;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    @Size(min = 8, message = "La contraseña debe tener mínimo 8 caracteres.")
    @Pattern(regexp = Constantes.CONTRASENA_PATTERN, message = Constantes.MENSAJE_CONTRASENA_PATTERN)
    private String password;

    private List<PhoneRequest> phones;
}
