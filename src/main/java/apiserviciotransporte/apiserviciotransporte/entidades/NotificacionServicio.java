package apiserviciotransporte.apiserviciotransporte.entidades;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "notificacion_servicio")
public class NotificacionServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_tiposervicio")
    @GenericGenerator(name = "secuencia_tiposervicio", strategy = "increment")
    @Column(name = "id")
    private long id;

    @OneToOne
    @JoinColumn(name = "usuario_notificacion", referencedColumnName = "id")
    private Usuario usuarioNotificacion;

    @Column(name = "mensaje", nullable = false, updatable = false)
    private String mensaje;

    @Column(name = "estado")
    private String estado;

}
