package apiserviciotransporte.apiserviciotransporte.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "solicitud_servicios")
public class SolicitudServicio {

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

    @Column(name = "cantidad_pasajeros")
    private int cantidadPasajeros;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.DETACH,
                    CascadeType.MERGE,
                    CascadeType.REFRESH})
    @JoinColumn(name = "tipo_vehiculo", referencedColumnName = "id" /*, nullable = false, updatable = false*/)
    private TipoServicio tipo;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "condiciones_servicio", referencedColumnName = "id"/*, nullable = false, updatable = false*/)
    private SolicitudAdicional condicionesServicio;

    @Column(name = "paradas_intermedias")
    private boolean paradasIntermedias;

    @Column(name = "inmediato")
    private boolean inmediato;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "activa")
    private boolean activa;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "solicitud_servicio", referencedColumnName = "id")
    private List<SolicitudParada> paradas;
}
