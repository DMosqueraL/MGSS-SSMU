package apiserviciotransporte.apiserviciotransporte.servicios;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudServicioDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudesServicioResponseDto;
import apiserviciotransporte.apiserviciotransporte.entidades.*;
import apiserviciotransporte.apiserviciotransporte.excepciones.NotFoundException;
import apiserviciotransporte.apiserviciotransporte.interfaces.SolicitudServicioService;
import apiserviciotransporte.apiserviciotransporte.mappers.SolicitudServicioMapper;
import apiserviciotransporte.apiserviciotransporte.repositorios.SolicitudServicioRepository;
import apiserviciotransporte.apiserviciotransporte.repositorios.TipoServicioRepository;
import apiserviciotransporte.apiserviciotransporte.repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SolicitudServicioServiceImpl implements SolicitudServicioService {

    private final SolicitudServicioRepository solicitudServicioRepository;
    private final UsuarioRepository usuarioRepository;
    private final TipoServicioRepository tipoServicioRepository;
    private final SolicitudServicioMapper mapper;

    @Override
    public SolicitudesServicioResponseDto listar(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<SolicitudServicio> solicitudesPage = this.solicitudServicioRepository.findAllByActiva(true, pageRequest);
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
    public List<SolicitudServicio> buscarPorUsuario(String idUsuario) {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(idUsuario);
        return usuarioOptional.map(solicitudServicioRepository::findByUsuario)
                .orElseThrow(() -> new NotFoundException("Usuario no encontrado"));
    }

    @Override
    public SolicitudServicioDto guardarSolicitudServicio(SolicitudServicioDto solicitudServicioDto) {
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findById(solicitudServicioDto.getUsuarioId());
        if (usuarioOptional.isEmpty()) {
            throw new NotFoundException("No se encontró el usuario con id: " + solicitudServicioDto.getUsuarioId());
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

    @Override
    public boolean eliminar(Long id) {
        try {
            return this.solicitudServicioRepository.findById(id).map(solicitudServicio -> {
                solicitudServicio.setActiva(false);
                solicitudServicioRepository.save(solicitudServicio);
                return Boolean.TRUE;
            }).orElse(Boolean.FALSE);
        } catch (Exception ex) {
            return false;
        }
    }
//
//    /***** Adicionales *****/
//
//    @Override
//    public List<SolicitudAdicional> listarAdicionales(Long id) {
//        // Buscar la solicitud por su ID
//        var solicitudServicioBuscada = solicitudServicioRepository.findById(id);
//
//        if (solicitudServicioBuscada.isPresent()) {
//            var solicitudServicio = solicitudServicioBuscada.get();
//
//        }
//        return null;
//    }

//    public void agregarAdicional(Long id, SolicitudAdicional solicitudAdicionales) {
//        // Buscar la solicitud por su ID
//        var solicitudServicioBuscada = solicitudServicioRepository.findById(id);
//
//        if (solicitudServicioBuscada.isPresent()) {
//            var solicitudServicio = solicitudServicioBuscada.get();
//
//            // Agregar el adicional a la lista de adicionales de la solicitud
//            // solicitudServicio.getCondicionesServicio().add(solicitudAdicionales);
//
//            // Actualizar el autor en la base de datos
//            solicitudServicioRepository.save(solicitudServicio);
//        } else {
//            // Manejar el caso en que no se encuentra la solicitud
//            throw new EntityNotFoundException("No se encontró la Solicitud de Servicio con ID: " + id);
//        }
//    }
//
//    /***** Paradas *****/
//
//    @Override
//    public List<SolicitudParada> listarParadas(Long id) {
//        // Buscar la solicitud por su ID
//        var solicitudServicioBuscada = solicitudServicioRepository.findById(id);
//
//        if (solicitudServicioBuscada.isPresent()) {
//            var solicitudServicio = solicitudServicioBuscada.get();
//
//            return solicitudServicio.getParadas();
//        }
//        return null;
//    }
//
//    public void agregarParada(Long id, SolicitudParada solicitudParada) {
//        // Buscar la solicitud por su ID
//        var solicitudServicioBuscada = solicitudServicioRepository.findById(id);
//
//        if (solicitudServicioBuscada.isPresent()) {
//            var solicitudServicio = solicitudServicioBuscada.get();
//
//            // Agregar el adicional a la lista de adicionales de la solicitud
//            solicitudServicio.getParadas().add(solicitudParada);
//
//            // Actualizar el autor en la base de datos
//            solicitudServicioRepository.save(solicitudServicio);
//        } else {
//            // Manejar el caso en que no se encuentra la solicitud
//            throw new EntityNotFoundException("No se encontró la Solicitud de Servicio con ID: " + id);
//        }
//    }

}
