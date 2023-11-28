package apiserviciotransporte.apiserviciotransporte.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
    private String id;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "contrasena", length = 300)
    private String contrasena;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private List<SolicitudServicio> solicitudesServicio;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "detail_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
