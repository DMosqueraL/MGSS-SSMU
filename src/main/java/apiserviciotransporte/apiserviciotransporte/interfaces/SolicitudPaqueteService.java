package apiserviciotransporte.apiserviciotransporte.interfaces;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudPaqueteDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudesPaqueteResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface SolicitudPaqueteService {

    SolicitudesPaqueteResponseDto listar(int page, int size);
    SolicitudPaqueteDto obtener(Long id);
    List<SolicitudPaqueteDto> buscarPorFecha(LocalDateTime fecha);
    List<SolicitudPaqueteDto> buscarPorUsuario(String idUsuario);
    SolicitudPaqueteDto guardarSolicitudPaquete(SolicitudPaqueteDto solicitud);
    boolean eliminar(Long id);
}
