package apiserviciotransporte.apiserviciotransporte.interfaces;

import apiserviciotransporte.apiserviciotransporte.controladores.dto.SolicitudServicioDto;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudAdicional;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudParada;
import apiserviciotransporte.apiserviciotransporte.entidades.SolicitudServicio;

import java.util.Date;
import java.util.List;

public interface SolicitudServicioService {

    List<SolicitudServicio> listar();

    SolicitudServicio obtener(Long id);

    List<SolicitudServicio> buscarPorFecha(Date fecha);

    List<SolicitudServicio> buscarPorUsuario(String usuario);

    SolicitudServicio guardarSolicitudServicio(SolicitudServicioDto solicitud);

    boolean eliminar(Long id);

    List<SolicitudAdicional> listarAdicionales(Long id);

    void agregarAdicional(Long id, SolicitudAdicional solicitudAdicionales);

    List<SolicitudParada> listarParadas(Long id);

    void agregarParada(Long id, SolicitudParada solicitudParada);

}
