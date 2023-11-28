package apiserviciotransporte.apiserviciotransporte.servicios;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.UsuarioDto;
import apiserviciotransporte.apiserviciotransporte.entidades.Usuario;
import apiserviciotransporte.apiserviciotransporte.excepciones.NotFoundException;
import apiserviciotransporte.apiserviciotransporte.interfaces.UsuarioService;
import apiserviciotransporte.apiserviciotransporte.mappers.UsuarioMapper;
import apiserviciotransporte.apiserviciotransporte.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public List<UsuarioDto> listar() {
        return this.usuarioRepository.findAll()
                .stream()
                .map(this.usuarioMapper::toDto)
                .toList();
    }

    @Override
    public UsuarioDto obtener(String id) {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(id);
        return usuarioOptional.map(this.usuarioMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
    }

    @Override
    public void eliminar(String id) {
        this.usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioDto guardar(UsuarioDto usuarioDto) {
        Usuario entidad = this.usuarioMapper.toEntity(usuarioDto);
        Usuario usuarioGuardado = this.usuarioRepository.save(entidad);
        return this.usuarioMapper.toDto(usuarioGuardado);
    }

    @Override
    public UsuarioDto buscarPorEmail(String email) {
        return this.usuarioRepository.findByEmail(email)
                .map(this.usuarioMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Usuario con email" + email + " no encontrado"));
    }
}
