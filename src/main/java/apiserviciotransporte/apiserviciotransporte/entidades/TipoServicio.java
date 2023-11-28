package apiserviciotransporte.apiserviciotransporte.entidades;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudServicioDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tipo_servicio")
public class TipoServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_tiposervicio")
    @GenericGenerator(name = "secuencia_tiposervicio", strategy = "increment")
    @Column(name = "id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private SolicitudServicioDto.EnumTipoServicio tipo;
}
