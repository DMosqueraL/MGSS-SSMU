package apiserviciotransporte.apiserviciotransporte.servicios;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudPaqueteDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudesPaqueteResponseDto;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudPaquete;
import apiserviciotransporte.apiserviciotransporte.entidades.Usuario;
import apiserviciotransporte.apiserviciotransporte.excepciones.NotFoundException;
import apiserviciotransporte.apiserviciotransporte.interfaces.SolicitudPaqueteService;
import apiserviciotransporte.apiserviciotransporte.mappers.SolicitudPaqueteMapper;
import apiserviciotransporte.apiserviciotransporte.repositorios.SolicitudPaqueteRepository;
import apiserviciotransporte.apiserviciotransporte.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SolicitudPaqueteServiceImpl implements SolicitudPaqueteService {

    private final SolicitudPaqueteRepository solicitudPaqueteRepository;
    private final UsuarioRepository usuarioRepository;
    private final SolicitudPaqueteMapper mapper;

    @Override
    public SolicitudesPaqueteResponseDto listar(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<SolicitudPaquete> solicitudesPage = this.solicitudPaqueteRepository.findAllByActiva(true, pageRequest);
        List<SolicitudPaqueteDto> solicitudesServicio = solicitudesPage.getContent().stream().map(this.mapper::toDto).toList();

        return SolicitudesPaqueteResponseDto.builder()
                .paginationInfo(SolicitudesPaqueteResponseDto.PaginationInfo.builder()
                        .currentPage(solicitudesPage.getNumber())
                        .currentElements(solicitudesPage.getNumberOfElements())
                        .totalElements(solicitudesPage.getTotalElements())
                        .totalPages(solicitudesPage.getTotalPages())
                        .build())
                .elements(solicitudesServicio)
                .build();

    }

    @Override
    public SolicitudPaqueteDto obtener(Long id) {
        Optional<SolicitudPaquete> servicioOptional = solicitudPaqueteRepository.findById(id);
        return servicioOptional.map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("No se encontró la solicitud de servicio con id: " + id));
    }

    @Override
    public List<SolicitudPaqueteDto> buscarPorFecha(LocalDateTime fecha) {
        return solicitudPaqueteRepository.findByFecha(fecha)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<SolicitudPaqueteDto> buscarPorUsuario(String idUsuario) {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(idUsuario);
        return usuarioOptional.map(user -> solicitudPaqueteRepository.findByUsuario(user)
                        .stream()
                        .map(this.mapper::toDto)
                        .toList()
                )
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
    }

    @Override
    public SolicitudPaqueteDto guardarSolicitudPaquete(SolicitudPaqueteDto solicitudServicioDto) {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(solicitudServicioDto.getUsuarioId());
        if (usuarioOptional.isEmpty()) {
            throw new NotFoundException("No se encontró el usuario con id: " + solicitudServicioDto.getUsuarioId());
        }
        Usuario usuario = usuarioOptional.get();

        SolicitudPaquete entity = this.mapper.toEntity(solicitudServicioDto);
        entity.setUsuario(usuario);
        SolicitudPaquete solicitudGuardada = solicitudPaqueteRepository.save(entity);
        return this.mapper.toDto(solicitudGuardada);
    }

    @Override
    public boolean eliminar(Long id) {
        try {
            return this.solicitudPaqueteRepository.findById(id).map(solicitudServicio -> {
                solicitudServicio.setActiva(false);
                solicitudPaqueteRepository.save(solicitudServicio);
                return Boolean.TRUE;
            }).orElse(Boolean.FALSE);
        } catch (Exception ex) {
            return false;
        }
    }
}
