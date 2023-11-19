package apiserviciotransporte.apiserviciotransporte.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "paquete")
public class SolicitudPaquete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @Column(name = "origen", nullable = false, updatable = false)
    private String origen;

    @Column(name = "destino", nullable = false, updatable = false)
    private String destino;

//    @Min(value = 0, message = "Valor mínimo permitido es 0")
//    @Column(name = "largo", nullable = false, updatable = false)
//    private int largo;
//
//    @Min(value = 0, message = "Valor mínimo permitido es 0")
//    @Column(name = "ancho", nullable = false, updatable = false)
//    private int ancho;
//
//    @Min(value = 0, message = "Valor mínimo permitido es 0")
//    @Column(name = "alto", nullable = false, updatable = false)
//    private int alto;

    @Min(value = 0, message = "Valor mínimo permitido es 0")
    @Column(name = "peso", nullable = false, updatable = false)
    private int peso;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "alimento-mercado")
    private boolean alimentoMercado;

}
