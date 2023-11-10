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
@Table(name = "tipo_adicional")
public class TipoAdicional {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_tipoadicional")
    @GenericGenerator(name = "secuencia_tipoadicional", strategy = "increment")
    @Column(name = "id")
    private long id;

    @Column(name = "tipo", length = 100, unique = true)
    private String tipo;

}
