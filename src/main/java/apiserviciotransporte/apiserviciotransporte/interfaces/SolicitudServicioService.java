package apiserviciotransporte.apiserviciotransporte.interfaces;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudServicioDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudesServicioResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface SolicitudServicioService {

    SolicitudesServicioResponseDto listar(int page, int size, SolicitudServicioDto.TipoSolicitud type);

    SolicitudesServicioResponseDto listarPorUsuario(int page, int size, SolicitudServicioDto.TipoSolicitud type);

    SolicitudServicioDto obtener(Long id);

    List<SolicitudServicioDto> buscarPorFecha(LocalDateTime fecha);

    SolicitudServicioDto guardarSolicitudServicio(SolicitudServicioDto solicitud);

    boolean eliminar(Long id);

}
