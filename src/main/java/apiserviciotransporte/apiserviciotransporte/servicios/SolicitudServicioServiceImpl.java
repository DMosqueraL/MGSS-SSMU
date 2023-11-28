package apiserviciotransporte.apiserviciotransporte.servicios;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudServicioDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudesServicioResponseDto;
import apiserviciotransporte.apiserviciotransporte.entidades.DetalleUsuario;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudServicio;
import apiserviciotransporte.apiserviciotransporte.entidades.TipoServicio;
import apiserviciotransporte.apiserviciotransporte.entidades.Usuario;
import apiserviciotransporte.apiserviciotransporte.excepciones.NotFoundException;
import apiserviciotransporte.apiserviciotransporte.interfaces.SolicitudServicioService;
import apiserviciotransporte.apiserviciotransporte.mappers.SolicitudServicioMapper;
import apiserviciotransporte.apiserviciotransporte.repositorios.SolicitudServicioRepository;
import apiserviciotransporte.apiserviciotransporte.repositorios.TipoServicioRepository;
import apiserviciotransporte.apiserviciotransporte.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SolicitudServicioServiceImpl implements SolicitudServicioService {

    private final SolicitudServicioRepository solicitudServicioRepository;
    private final UsuarioRepository usuarioRepository;
    private final TipoServicioRepository tipoServicioRepository;
    private final SolicitudServicioMapper mapper;

    @Override
    public SolicitudesServicioResponseDto listar(int page, int size, SolicitudServicioDto.TipoSolicitud type) {
        DetalleUsuario user = getPrincipal();

        PageRequest pageRequest = PageRequest.of(page, size);
        boolean inmediato = type == SolicitudServicioDto.TipoSolicitud.INMEDIATA;
        Page<SolicitudServicio> solicitudesPage = this.solicitudServicioRepository.findAllByActivaAndInmediato(true, inmediato, pageRequest);
        List<SolicitudServicioDto> solicitudesServicio = solicitudesPage.getContent().stream().map(this.mapper::toDto).toList();

        return SolicitudesServicioResponseDto.builder()
                .paginationInfo(SolicitudesServicioResponseDto.PaginationInfo.builder()
                        .currentPage(solicitudesPage.getNumber())
                        .currentElements(solicitudesPage.getNumberOfElements())
                        .totalElements(solicitudesPage.getTotalElements())
                        .totalPages(solicitudesPage.getTotalPages())
                        .build())
                .elements(solicitudesServicio)
                .build();

    }

    @Override
    public SolicitudesServicioResponseDto listarPorUsuario(int page, int size, SolicitudServicioDto.TipoSolicitud type) {
        DetalleUsuario user = getPrincipal();

        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(user.getId());
        return usuarioOptional.map(usuario -> {
                    PageRequest pageRequest = PageRequest.of(page, size);
                    boolean inmediato = type == SolicitudServicioDto.TipoSolicitud.INMEDIATA;
                    Page<SolicitudServicio> solicitudesPage = this.solicitudServicioRepository.findAllByUsuarioAndActivaAndInmediato(usuario, true, inmediato, pageRequest);
                    List<SolicitudServicioDto> solicitudesServicio = solicitudesPage.getContent().stream().map(this.mapper::toDto).toList();

                    return SolicitudesServicioResponseDto.builder()
                            .paginationInfo(SolicitudesServicioResponseDto.PaginationInfo.builder()
                                    .currentPage(solicitudesPage.getNumber())
                                    .currentElements(solicitudesPage.getNumberOfElements())
                                    .totalElements(solicitudesPage.getTotalElements())
                                    .totalPages(solicitudesPage.getTotalPages())
                                    .build())
                            .elements(solicitudesServicio)
                            .build();
                })
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));


    }

    @Override
    public SolicitudServicioDto obtener(Long id) {
        Optional<SolicitudServicio> servicioOptional = solicitudServicioRepository.findById(id);
        return servicioOptional.map(mapper::toDto)
                .orElseThrow(() -> new NotFoundException("No se encontró la solicitud de servicio con id: " + id));
    }

    @Override
    public List<SolicitudServicioDto> buscarPorFecha(LocalDateTime fecha) {
        return solicitudServicioRepository.findByFecha(fecha)
                .stream()
                .map(mapper::toDto)
                .toList();
    }


    @Override
    public SolicitudServicioDto guardarSolicitudServicio(SolicitudServicioDto solicitudServicioDto) {
        DetalleUsuario user = getPrincipal();
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(user.getId());
        if (usuarioOptional.isEmpty()) {
            throw new NotFoundException("No se encontró el usuario con id: " + user.getId());
        }
        Optional<TipoServicio> tipoServicioOptional = this.tipoServicioRepository.findByTipo(solicitudServicioDto.getTipo());
        if (tipoServicioOptional.isEmpty()) {
            throw new NotFoundException("No se encontró el tipo de servicio: " + solicitudServicioDto.getTipo());
        }
        Usuario usuario = usuarioOptional.get();
        TipoServicio tipoServicio = tipoServicioOptional.get();

        SolicitudServicio entity = this.mapper.toEntity(solicitudServicioDto, tipoServicio);
        entity.setUsuario(usuario);
        entity.setActiva(true);
        SolicitudServicio solicitudGuardada = solicitudServicioRepository.save(entity);
        return this.mapper.toDto(solicitudGuardada);
    }

    private static DetalleUsuario getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (DetalleUsuario) authentication.getPrincipal();
    }

    @Override
    public boolean eliminar(Long id) {
        DetalleUsuario user = getPrincipal();
        try {
            return this.solicitudServicioRepository.findById(id).map(solicitudServicio -> {
                if(!solicitudServicio.getUsuario().getId().equals(user.getId())) {
                    throw new NotFoundException("No se encontró la solicitud de servicio con id: " + id);
                }
                solicitudServicio.setActiva(false);
                solicitudServicioRepository.save(solicitudServicio);
                return Boolean.TRUE;
            }).orElse(Boolean.FALSE);
        } catch (Exception ex) {
            return false;
        }
    }
}
