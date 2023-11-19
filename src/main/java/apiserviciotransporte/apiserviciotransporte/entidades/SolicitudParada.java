package apiserviciotransporte.apiserviciotransporte.entidades;

import jakarta.persistence.*;
import lombok.*;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "solicitud_paradas")
public class SolicitudParada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private SolicitudServicio solicitud;

    @Column(name = "direccion", length = 100)
    private String direccion;

}
