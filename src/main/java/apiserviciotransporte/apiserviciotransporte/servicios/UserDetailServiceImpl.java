package apiserviciotransporte.apiserviciotransporte.servicios;

import apiserviciotransporte.apiserviciotransporte.entidades.DetalleUsuario;
import apiserviciotransporte.apiserviciotransporte.entidades.Usuario;
import apiserviciotransporte.apiserviciotransporte.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findByEmail(email);
        if(usuarioOptional.isEmpty()) {
            throw new UsernameNotFoundException("El usuario con el correo " + email + " no existe");
        }
        Usuario usuario = usuarioOptional.get();
        DetalleUsuario detalleUsuario = DetalleUsuario.build(usuario);
        return detalleUsuario;
    }

}
