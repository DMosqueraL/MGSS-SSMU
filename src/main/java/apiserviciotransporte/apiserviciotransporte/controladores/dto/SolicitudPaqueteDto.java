package apiserviciotransporte.apiserviciotransporte.controladores.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SolicitudPaqueteDto {

    private Long id;

    private boolean activa;

    @NotEmpty(message = "El id del usuario no puede ser vacío")
    @Pattern(regexp = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}", message = "El id del usuario no es un uuid")
    private String usuarioId;

    @NotEmpty(message = "El origen no puede ser vacio")
    private String origen;

    @NotEmpty(message = "El destino no puede ser vacio")
    private String destino;

    @NotNull(message = "Las dimensiones del paquete deben estar definidas")
    private SolicitudPaqueteDto.DimensionesPaqueteDto dimensiones;

    @NotNull(message = "Debe especificar si se trata de alimentos o mercado")
    private Boolean alimentosOMercado;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime fecha;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class DimensionesPaqueteDto {
        private int ancho;
        private int alto;
        private int largo;
        private int peso;
    }
}
