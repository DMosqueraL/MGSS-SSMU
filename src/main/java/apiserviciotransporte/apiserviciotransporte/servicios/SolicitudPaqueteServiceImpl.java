package apiserviciotransporte.apiserviciotransporte.servicios;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudPaqueteDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudServicioDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudesPaqueteResponseDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudesServicioResponseDto;
import apiserviciotransporte.apiserviciotransporte.entidades.*;
import apiserviciotransporte.apiserviciotransporte.excepciones.NotFoundException;
import apiserviciotransporte.apiserviciotransporte.interfaces.SolicitudPaqueteService;
import apiserviciotransporte.apiserviciotransporte.mappers.SolicitudPaqueteMapper;
import apiserviciotransporte.apiserviciotransporte.repositorios.SolicitudPaqueteRepository;
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
import java.util.stream.Collectors;

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
    public SolicitudesPaqueteResponseDto listarPorUsuario(int page, int size) {
        DetalleUsuario user = getPrincipal();

        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(user.getId());
        return usuarioOptional.map(usuario -> {
                    PageRequest pageRequest = PageRequest.of(page, size);
                    Page<SolicitudPaquete> solicitudesPage = this.solicitudPaqueteRepository.findAllByUsuarioAndActiva(usuario, true, pageRequest);
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
                })
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));


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
    public SolicitudPaqueteDto guardarSolicitudPaquete(SolicitudPaqueteDto solicitudServicioDto) {
        DetalleUsuario detalleUsuario = getPrincipal();
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(detalleUsuario.getId());
        if (usuarioOptional.isEmpty()) {
            throw new NotFoundException("No se encontró el usuario con id: " + detalleUsuario.getId());
        }
        Usuario usuario = usuarioOptional.get();

        SolicitudPaquete entity = this.mapper.toEntity(solicitudServicioDto);
        entity.setUsuario(usuario);
        entity.setActiva(true);
        SolicitudPaquete solicitudGuardada = solicitudPaqueteRepository.save(entity);
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
            return this.solicitudPaqueteRepository.findById(id).map(solicitudServicio -> {
                if(!solicitudServicio.getUsuario().getId().equals(user.getId())) {
                    throw new NotFoundException("No se encontró la solicitud de servicio con id: " + id);
                }
                solicitudServicio.setActiva(false);
                solicitudPaqueteRepository.save(solicitudServicio);
                return Boolean.TRUE;
            }).orElse(Boolean.FALSE);
        } catch (Exception ex) {
            return false;
        }
    }
}
