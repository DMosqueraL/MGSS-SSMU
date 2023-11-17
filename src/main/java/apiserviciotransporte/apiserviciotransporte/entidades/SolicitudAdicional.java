package apiserviciotransporte.apiserviciotransporte.entidades;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "solicitud_adicionales")
public class SolicitudAdicional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "mascotas", updatable = false, nullable = false)
    private boolean mascotas;
//
//    @Column(name = "paquetes", updatable = false, nullable = false)
//    private boolean paquetes;

    @Column(name = "maletas", updatable = false, nullable = false)
    private boolean maletas;
}
