package apiserviciotransporte.apiserviciotransporte.interfaces;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudServicioDto;
import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudesServicioResponseDto;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudServicio;

import java.time.LocalDateTime;
import java.util.List;

public interface SolicitudServicioService {

    SolicitudesServicioResponseDto listar(int page, int size, SolicitudServicioDto.TipoSolicitud type);

    SolicitudServicioDto obtener(Long id);

    List<SolicitudServicioDto> buscarPorFecha(LocalDateTime fecha);

    List<SolicitudServicio> buscarPorUsuario(String usuario);

    SolicitudServicioDto guardarSolicitudServicio(SolicitudServicioDto solicitud);

    boolean eliminar(Long id);

}
