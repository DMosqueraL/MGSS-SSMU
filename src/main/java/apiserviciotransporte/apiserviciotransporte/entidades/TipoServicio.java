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
@Table(name = "tiposervicio")
public class TipoServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_tiposervicio")
    @GenericGenerator(name = "secuencia_tiposervicio", strategy = "increment")
    @Column(name = "id")
    private long id;

    @Column(name = "tipo", length = 100, unique = true)
    private String tipo;

}
