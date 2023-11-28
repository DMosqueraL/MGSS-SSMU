package apiserviciotransporte.apiserviciotransporte.servicios;

import apiserviciotransporte.apiserviciotransporte.config.security.jwt.JwtProvider;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.UserLoginRequestDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.UserLoginResponseDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.UserRegisterRequestDto;
import apiserviciotransporte.apiserviciotransporte.entidades.DetalleUsuario;
import apiserviciotransporte.apiserviciotransporte.entidades.Role;
import apiserviciotransporte.apiserviciotransporte.entidades.Usuario;
import apiserviciotransporte.apiserviciotransporte.excepciones.ConflictException;
import apiserviciotransporte.apiserviciotransporte.excepciones.NotFoundException;
import apiserviciotransporte.apiserviciotransporte.interfaces.AuthService;
import apiserviciotransporte.apiserviciotransporte.mappers.UsuarioMapper;
import apiserviciotransporte.apiserviciotransporte.repositorios.RoleRepository;
import apiserviciotransporte.apiserviciotransporte.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authManagerBuilder;
    private final JwtProvider jwtProvider;

    @Override
    public void register(UserRegisterRequestDto userRegisterRequestDto) {
        boolean existByEmail = this.usuarioRepository.existsByEmail(userRegisterRequestDto.getEmail());
        if (existByEmail) {
            throw new ConflictException("El usuario con el correo " + userRegisterRequestDto.getEmail() + " ya existe");
        }
        Optional<Role> roleOptional = this.roleRepository.findByRole(userRegisterRequestDto.getRole());
        if (roleOptional.isEmpty()) {
            throw new ConflictException("El rol " + userRegisterRequestDto.getRole() + " no existe");
        }
        Usuario entity = this.mapper.toEntity(userRegisterRequestDto)
                .toBuilder()
                .contrasena(this.passwordEncoder.encode(String.valueOf(userRegisterRequestDto.getPassword())))
                .roles(Set.of(roleOptional.get()))
                .build();

        this.usuarioRepository.save(entity);
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto) {
        try {
            Authentication authentication =
                    this.authManagerBuilder
                            .getObject()
                            .authenticate(new UsernamePasswordAuthenticationToken(
                                    userLoginRequestDto.getEmail(),
                                    userLoginRequestDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            DetalleUsuario user = (DetalleUsuario) authentication.getPrincipal();

            return this.jwtProvider.generateUserCredentials(user);
        } catch (DisabledException | LockedException | BadCredentialsException e1) {
            e1.printStackTrace(System.out);
        }
        return null;
    }

    @Override
    public UserLoginResponseDto refreshToken(String email) {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findByEmail(email);
        if (usuarioOptional.isEmpty()) {
            throw new NotFoundException("El usuario con el correo " + email + " no existe");
        }
        Usuario usuario = usuarioOptional.get();
        DetalleUsuario detalleUsuario = DetalleUsuario.build(usuario);
        UserLoginResponseDto userLoginResponseDto = this.jwtProvider.generateUserCredentials(detalleUsuario);
        return userLoginResponseDto;


    }
}
