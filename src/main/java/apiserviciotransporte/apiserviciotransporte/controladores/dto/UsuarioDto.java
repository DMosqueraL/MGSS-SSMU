package apiserviciotransporte.apiserviciotransporte.controladores.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDto {

    private String id;

    @NotEmpty(message = "El email no puede ser vacio")
    @Pattern(regexp =  "^[A-Za-z0-9+_.-]+@(.+)$", message = "El email no es valido")
    private String email;

    @NotEmpty(message = "El nombre completo no puede ser vacio")
    private String nombreCompleto;

    @NotEmpty(message = "El telefono no puede ser vacio")
    private String telefono;

}
