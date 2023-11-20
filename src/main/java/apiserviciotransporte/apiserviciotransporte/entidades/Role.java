package apiserviciotransporte.apiserviciotransporte.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private UserRoles role;

    @Getter
    public enum UserRoles {

        ROLE_SOCIO("ROLE_SOCIO"),
        ROLE_USUARIO("ROLE_USUARIO");

        public final String label;
        UserRoles(String label) {
            this.label = label;
        }


    }


}
