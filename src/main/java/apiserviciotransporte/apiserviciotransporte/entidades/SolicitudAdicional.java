package apiserviciotransporte.apiserviciotransporte.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

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

    @ManyToOne
    private SolicitudServicio solicitud;


    @ManyToOne
    @JoinColumn(name = "idtipo", referencedColumnName = "id")
    private TipoAdicional tipo;


    @Column(name = "cantidad")
    private int cantidad;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolicitudAdicional that = (SolicitudAdicional) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
