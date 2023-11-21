package apiserviciotransporte.apiserviciotransporte.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "solicitudes_paquetes")
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

    @Column(name = "activa")
    private boolean activa;

    @Column(name = "destino", nullable = false, updatable = false)
    private String destino;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "dimensiones_paquete_id", referencedColumnName = "id")
    private DimensionesPaquete dimensiones;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "alimento-mercado")
    private boolean alimentosOMercado;

}
