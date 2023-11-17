package apiserviciotransporte.apiserviciotransporte.interfaces;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudServicioDto;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudAdicional;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudParada;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudServicio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface SolicitudServicioService {

    List<SolicitudServicioDto> listar();

    SolicitudServicioDto obtener(Long id);

    List<SolicitudServicioDto> buscarPorFecha(LocalDateTime fecha);

    List<SolicitudServicio> buscarPorUsuario(String usuario);

    SolicitudServicioDto guardarSolicitudServicio(SolicitudServicioDto solicitud);

    boolean eliminar(Long id);

//    List<SolicitudAdicional> listarAdicionales(Long id);
//
//    void agregarAdicional(Long id, SolicitudAdicional solicitudAdicionales);
//
//    List<SolicitudParada> listarParadas(Long id);
//
//    void agregarParada(Long id, SolicitudParada solicitudParada);

}
