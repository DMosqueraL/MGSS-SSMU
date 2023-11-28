package apiserviciotransporte.apiserviciotransporte.controladores.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SolicitudServicioDto {

    private Long id;

    private boolean activa;

    @NotEmpty(message = "El origen no puede ser vacio")
    private String origen;

    @NotEmpty(message = "El destino no puede ser vacio")
    private String destino;

    @Positive(message = "La cantidad de pasajeros debe ser mayor a 0")
    private int cantidadPasajeros;

    @NotNull(message = "El tipo de vehiculo no puede ser vacio")
    private EnumTipoServicio tipo;

    @NotNull(message = "Las condiciones del servicio no pueden ser vacias")
    private SolicitudAdicional condicionesServicio;

    @NotNull(message = "El servicio inmediato no puede ser null")
    private Boolean inmediato;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime fecha;

    @NotNull(message = "Las paradas no pueden ser null")
    private List<String> paradas;

    @Getter
    @NoArgsConstructor
    public enum EnumTipoServicio {
        LUJO("LUJO"), NORMAL("NORMAL");

        private  String tipo;
        EnumTipoServicio(String tipo) {
            this.tipo = tipo;
        }
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class SolicitudAdicional {
        private boolean mascotas;
        private boolean maletas;
    }

    @AllArgsConstructor
    @Getter
    public enum TipoSolicitud {
        INMEDIATA("INMEDIATA"),
        RESERVADA("RESERVADA");

        private String tipo;

    }


}
