package apiserviciotransporte.apiserviciotransporte.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dimensiones_paquete")
public class DimensionesPaquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Min(value = 0, message = "Valor mínimo permitido es 0")
    @Column(name = "largo", nullable = false, updatable = false)
    private int largo;

    @Min(value = 0, message = "Valor mínimo permitido es 0")
    @Column(name = "ancho", nullable = false, updatable = false)
    private int ancho;

    @Min(value = 0, message = "Valor mínimo permitido es 0")
    @Column(name = "alto", nullable = false, updatable = false)
    private int alto;

    @Min(value = 0, message = "Valor mínimo permitido es 0")
    @Column(name = "peso", nullable = false, updatable = false)
    private int peso;

}
