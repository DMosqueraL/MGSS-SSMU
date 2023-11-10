package apiserviciotransporte.apiserviciotransporte.entidades;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "solicitud_servicio")
public class SolicitudServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idusuario", referencedColumnName = "id")
    private Usuario usuario;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "cantidadpasajeros")
    private int cantidadPasajeros;

    @Column(name = "inmediato")
    private boolean inmediato;

    @Column(name = "horaprogramada")
    private LocalTime horaProgramada;

    @Column(name = "origen")
    private String direccionOrigen;

    @Column(name = "destino")
    private String direccionDestino;

    @ManyToOne
    @JoinColumn(name = "tipo_vehiculo", referencedColumnName = "id")
    private TipoServicio tipo;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "solicitud_adicionales", referencedColumnName = "id")
    private List<SolicitudAdicional> adicionales;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "solicitud_paradas", referencedColumnName = "id")
    private List<SolicitudParada> paradas;

    public void addSolicitudAdicional(SolicitudAdicional solicitud){
        if (adicionales == null){
            adicionales = new ArrayList<>();
        }
        adicionales.add(solicitud);
    }
    public void addSolicitudParada(SolicitudParada solicitud){
        if (paradas == null){
            paradas = new ArrayList<>();
        }
        paradas.add(solicitud);
    }
    public void deleteSolicitudAdicional(SolicitudAdicional solicitud){
        if (adicionales == null){
            adicionales = new ArrayList<>();
        }
        adicionales.remove(solicitud);
    }
    public void deleteSolicitudParada(SolicitudParada solicitud){
        if (paradas == null){
            paradas = new ArrayList<>();
        }
        paradas.remove(solicitud);
    }
}
