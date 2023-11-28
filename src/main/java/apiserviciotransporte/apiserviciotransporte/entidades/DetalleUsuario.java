package apiserviciotransporte.apiserviciotransporte.entidades;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class DetalleUsuario implements UserDetails {

    private String id;
    private String email;
    private String name;
    private String phoneNumber;
    private String password;
    private Set<? extends GrantedAuthority> grantedAuthorities;

    public static DetalleUsuario build(Usuario usuario) {
        Set<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().name()))
                .collect(Collectors.toSet());

        return DetalleUsuario.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .password(usuario.getContrasena())
                .name(usuario.getNombreCompleto())
                .phoneNumber(usuario.getTelefono())
                .grantedAuthorities(authorities)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
